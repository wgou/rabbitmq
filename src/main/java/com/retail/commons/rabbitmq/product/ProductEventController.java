/**  
 * Project Name:retail-commons-rabbtimq  
 * File Name:ConsumerEventController.java  
 * Package Name:com.retail.commons.rabbtimq.consumer  
 * Date:2016年5月2日下午4:04:16  
 * Copyright (c) 2016, 成都瑞泰尔科技有限公司 All Rights Reserved.  
 *  
*/  
  
package com.retail.commons.rabbitmq.product;  

import java.io.IOException;

import org.apache.commons.lang.StringUtils;

import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.MessageProperties;
import com.rabbitmq.client.ShutdownSignalException;
import com.retail.commons.rabbitmq.DefaultEventController;
import com.retail.commons.rabbitmq.EventConfig;
import com.retail.commons.rabbitmq.EventProcesser;
import com.retail.commons.rabbitmq.code.CodeFactory;

/**  
 * 描述: <br/>消费者程序<br/>
 * ClassName:ConsumerEventController <br/>  
 * Date:     2016年5月2日 下午4:04:16 <br/>  
 * @author   苟伟(gouewi@retail-tek.com) 
 * @version enclosing_typetags  
 * @see        
 */
public class ProductEventController extends DefaultEventController{

	private static ProductEventController productEvent;
	public ProductEventController(EventConfig config) {
		super(config);  
	}
	public static synchronized ProductEventController getInstance(EventConfig config){
		if(productEvent == null)
			productEvent = new ProductEventController(config);
		return productEvent;
	}
	/**
	 * TODO 初始化生产者
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
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	 
	public void addMessageHandler(EventProcesser eventProcesser) {
		msgAdapterHandler.addHandler(eventProcesser);
	}
	
	public void addEncode(CodeFactory codeFactory){
		msgAdapterHandler.addEncode(codeFactory);
	}
	//消息发送
	public void send(Object data) throws Exception{
		Object newData = messageHandler(data);
		byte[] message = encode(newData);
		//持久化消息
		ch.basicPublish(exchange, queueName, MessageProperties.PERSISTENT_TEXT_PLAIN,message);
	}
}
  
