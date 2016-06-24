/**  
 * Project Name:retail-commons-rabbtimq  
 * File Name:ConsumerEventController.java  
 * Package Name:com.retail.commons.rabbtimq.consumer  
 * Date:2016年5月2日下午4:04:16  
 * Copyright (c) 2016, 成都瑞泰尔科技有限公司 All Rights Reserved.  
 *  
*/  
  
package com.retail.commons.rabbitmq.consumer;  

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.StringUtils;

import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;
import com.retail.commons.rabbitmq.DefaultEventController;
import com.retail.commons.rabbitmq.EventConfig;
import com.retail.commons.rabbitmq.EventProcesser;
import com.retail.commons.rabbitmq.code.CodeFactory;
import com.retail.commons.rabbitmq.exception.DecodeException;
import com.retail.commons.rabbitmq.exception.EventProcesserException;

/**  
 * 描述: <br/>TODO; <br/>
 * ClassName:ConsumerEventController <br/>  
 * Date:     2016年5月2日 下午4:04:16 <br/>  
 * @author   苟伟(gouewi@retail-tek.com) 
 * @version enclosing_typetags  
 * @see        
 */
public class ConsumerEventController extends DefaultEventController{
	//处理线程池
	private ExecutorService excutors ;
	private static ConsumerEventController consumerEvent;
	public ConsumerEventController(EventConfig config) {
		super(config);  
		excutors = Executors.newFixedThreadPool(config.getEventMsgProcessNum());
	}
	public static synchronized ConsumerEventController getInstance(EventConfig config){
		if(consumerEvent == null)
			consumerEvent = new ConsumerEventController(config);
		return consumerEvent;
	}
	/**
	 * TODO 初始化消费者
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws ConsumerCancelledException 
	 * @throws ShutdownSignalException 
	 * @see com.retail.commons.rabbitmq.DefaultEventController#init()
	 */
	@Override
	public void init() throws Exception{
		if(StringUtils.isEmpty(queueName)){
			throw new Exception("queueName can not null,please call setQueueAndExchange method ");
		}
		try{
			ch = rabbtiConnection.createChannel(config.getEventMsgProcessNum());
			//队列名称,服务器重启队列是否存活,是否声明一个仅限于此链接的独占队列,不使用此队列时服务器是否删除它，其他参数
			ch.queueDeclare(queueName, true, false, false, null);  
			QueueingConsumer consumer = new QueueingConsumer(ch);
			//收到应答后消息才会被移除
			ch.basicConsume(queueName,false, consumer);
			ch.basicQos(config.getPrefetchSize());
			 while (isStarted.get()) {  
		           final QueueingConsumer.Delivery delivery = consumer.nextDelivery();  
		            final byte[] data = delivery.getBody();
		            //将消息提交到线程池处理
		            excutors.submit(new Runnable() {
						public void run() {
							try {
								//解码 调用链
								Object object = decode(data);
								//handler 调用链
								messageHandler(object);
							} catch (DecodeException e) {
								logger.error("消息解码发生异常.",e);
							} catch (EventProcesserException e) {
								logger.error("消息处理handler发生异常.",e);
							} 
							try{
								//当消息被handler处理完成后发出ack确认,
								//消息如果处理发生异常,我们认为该消息下次还会发生该异常,所以向MQ发生ACK
								ch.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
							}catch (IOException e) {
								logger.error("向RabbitMQ发送ACK异常.",e);
							}
						}
					});
			 }
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	 
	public void addMessageHandler(EventProcesser eventProcesser) {
		msgAdapterHandler.addHandler(eventProcesser);
	}
	
	public void addDecode(CodeFactory codeFactory){
		msgAdapterHandler.addDecode(codeFactory);
	}

	 
}
  
