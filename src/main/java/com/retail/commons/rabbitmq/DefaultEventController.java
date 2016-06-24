/**  
 * Project Name:retail-commons-rabbtimq  
 * File Name:DefaultEventController.java  
 * Package Name:com.retail.commons.rabbtimq.consumer  
 * Date:2016年5月2日下午1:48:23  
 * Copyright (c) 2016, 成都瑞泰尔科技有限公司 All Rights Reserved.  
 *  
*/  
  
package com.retail.commons.rabbitmq;  

import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.retail.commons.rabbitmq.MessageAdapterHandler.EventProcessorDecode;
import com.retail.commons.rabbitmq.MessageAdapterHandler.EventProcessorEncode;
import com.retail.commons.rabbitmq.exception.ConsumerException;
import com.retail.commons.rabbitmq.exception.DecodeException;
import com.retail.commons.rabbitmq.exception.EncodeException;
import com.retail.commons.rabbitmq.exception.EventProcesserException;

/**  
 * 描述: <br/>TODO; <br/>
 * ClassName:DefaultEventController <br/>  
 * Date:     2016年5月2日 下午1:48:23 <br/>  
 * @author   苟伟(gouewi@retail-tek.com) 
 * @version enclosing_typetags  
 * @see        
 */
public abstract class DefaultEventController implements EventController{

	protected Logger logger = Logger.getLogger(DefaultEventController.class);
	//private static DefaultEventController defaultEventController;
	//连接配置
	protected EventConfig config;
	//连接实例对象
	protected Connection rabbtiConnection ;
	//可运行标记
	protected AtomicBoolean isStarted = new AtomicBoolean(false);
	//消息路由handler
	protected MessageAdapterHandler msgAdapterHandler = new MessageAdapterHandler();
	//声明队列名称
	protected String queueName;
	protected String exchange;
	//channel 信道
	protected Channel ch ;
	
	public DefaultEventController(EventConfig config){
		if (config == null) {
			throw new IllegalArgumentException("Config can not be null.");
		}
		this.config = config;
	}
	/*public synchronized static DefaultEventController getInstance(EventConfig config){
		if(defaultEventController==null){
			defaultEventController = new DefaultEventController(config);
		}
		return defaultEventController;
	}*/
	
	/**
	 * 初始化rabbitmq连接
	 * @throws TimeoutException 
	 * @throws IOException 
	 */
	private void initRabbitConnectionFactory() throws IOException, TimeoutException {
		ConnectionFactory rabbitConnectionFactory = new ConnectionFactory();
		rabbitConnectionFactory.setHost(config.getServerHost());
		//rabbitConnectionFactory.setRequestedChannelMax(config.getEventMsgProcessNum());
		rabbitConnectionFactory.setConnectionTimeout(config.getConnectionTimeout());
		rabbitConnectionFactory.setPort(config.getPort());
		rabbitConnectionFactory.setUsername(config.getUsername());
		rabbitConnectionFactory.setPassword(config.getPassword());
		if (!StringUtils.isEmpty(config.getVirtualHost())) {
			rabbitConnectionFactory.setVirtualHost(config.getVirtualHost());
		}
		rabbtiConnection = rabbitConnectionFactory.newConnection();
	}
	 
	/**
	 * 注销程序
	 */
	public synchronized void destroy() {
		if (!isStarted.get()) {
			return;
		}
		if(rabbtiConnection !=null)
			try {
				rabbtiConnection.close();
			} catch (IOException e) {
				logger.error("close rabbtiConnecction exception.",e);
			}
		isStarted.set(false);
	}
	/**
	 * 关闭通道
	 */
	public synchronized void close(){
		try {
			if(ch !=null)
				ch.close();
		} catch (IOException e) {
			logger.error("close channel exception.",e); 
		} catch (TimeoutException e) {
			logger.error("close channel timeout.",e);   
		}
	}
	
	/**
	 * 启动程序
	 */
	public void start() {
		 //可运行标记
		isStarted.set(true);
		try {
			initRabbitConnectionFactory();
		}catch (Exception e) {
			logger.error(e.getMessage(),e);  
			destroy();
		}
		try{
			init();
		} catch (ConsumerException e) {
			logger.error(e.getMessage(),e); 
			//destroy 消费者handler处理异常.不销毁rabbttiMQ连接 同时不做任何处理
		} catch (Exception e) {
			logger.error(e.getMessage(),e);  
			close();
			destroy();
		}
	}
	//解码
	protected Object decode(final byte[] data) throws DecodeException{
		Object newData  = data;
		Iterator<EventProcessorDecode> it = msgAdapterHandler.getDecodeList().iterator();
		while(it.hasNext()){
			EventProcessorDecode eventCode = it.next();
			newData = eventCode.process((byte[]) newData);
		}
		return newData;
	}
	
	//编码
	protected byte[] encode(final Object data) throws EncodeException{
		Iterator<EventProcessorEncode> it = msgAdapterHandler.getEncodeList().iterator();
		Object newData  = data;
		while(it.hasNext()){
			EventProcessorEncode eventCode = it.next();
			newData = eventCode.process(newData);
		}
		return (byte[]) newData;
	}
	
	//handler 
	protected Object messageHandler(final Object data) throws EventProcesserException{
		Object newData  = data;
		Iterator<EventProcesser> it = msgAdapterHandler.getHandlerList().iterator();
		while(it.hasNext()){
			EventProcesser processer = it.next();
			newData = processer.process(newData);
		}
		return newData;
	}
	
	//启动生产者 ，启动消费者
	public abstract void init() throws Exception ;

 
	public void setQueueAndExchange(String queueName,String exchange) {
		this.queueName = queueName;
		exchange = exchange ==null ? "": exchange;
		this.exchange=exchange;
	}
	
}
  
