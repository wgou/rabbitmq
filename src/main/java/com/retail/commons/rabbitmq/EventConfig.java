/**  
 * Project Name:retail-commons-rabbtimq  
 * File Name:EventConfig.java  
 * Package Name:com.retail.commons.rabbtimq  
 * Date:2016年5月2日下午12:16:20  
 * Copyright (c) 2016, 成都瑞泰尔科技有限公司 All Rights Reserved.  
 *  
*/  
  
package com.retail.commons.rabbitmq;  

import com.retail.commons.rabbitmq.code.CodeFactory;
import com.retail.commons.rabbitmq.code.HessianCodecFactory;


/**  
 * 描述: <br/>连接配置 <br/>
 * ClassName:EventConfig <br/>  
 * Date:     2016年5月2日 下午12:16:20 <br/>  
 * @author   苟伟(gouewi@retail-tek.com) 
 * @version enclosing_typetags  
 * @see        
 */
public class EventConfig {

	//默认端口信息
	private final static int DEFAULT_PORT = 5672; 
	
	private final static String DEFAULT_USERNAME="admin";
	
	private final static String DEFAULT_PASSWORD="admin";
	//默认处理线程,cpu核心数 * 2
	private final static int DEFAULT_PROCESS_THREAD_NUM = Runtime.getRuntime().availableProcessors() * 2;
	//默认消费者 公平消费策略,每次每个消费者只能处理一条消息
	private final static int PREFETCH_SIZE = 1;
	//默认连接RabbitMQ 超时时间
	private final static int DEFAULT_CONNECTION_TIMEOUT = 5*1000;
	private String serverHost ;
	
	private int port = DEFAULT_PORT;
	
	private String username = DEFAULT_USERNAME;
	
	private String password = DEFAULT_PASSWORD;
	
	private String virtualHost="/";
	
	/**
	 * 和rabbitmq建立连接的超时时间
	 */
	private int connectionTimeout = DEFAULT_CONNECTION_TIMEOUT;
	
	/**
	 * 事件消息处理线程数，默认是 CPU核数 * 2
	 */
	private int eventMsgProcessNum = DEFAULT_PROCESS_THREAD_NUM;
	
	/**
	 * 每次消费消息的预取值
	 */
	private int prefetchSize = PREFETCH_SIZE;
	
	public EventConfig(){}
	public EventConfig(String serverHost) {
		this(serverHost,DEFAULT_PORT,DEFAULT_USERNAME,DEFAULT_PASSWORD,null,DEFAULT_CONNECTION_TIMEOUT,
				DEFAULT_PROCESS_THREAD_NUM,PREFETCH_SIZE,new HessianCodecFactory());
	}

	public EventConfig(String serverHost, int port, String username,
			String password, String virtualHost, int connectionTimeout,
			int eventMsgProcessNum,int prefetchSize,CodeFactory defaultCodecFactory) {
		super();
		this.serverHost = serverHost;
		this.port = port>0?port:DEFAULT_PORT;
		this.username = username;
		this.password = password;
		this.virtualHost = virtualHost;
		this.connectionTimeout = connectionTimeout>0?connectionTimeout:0;
		this.eventMsgProcessNum = eventMsgProcessNum>0?eventMsgProcessNum:DEFAULT_PROCESS_THREAD_NUM;
		this.prefetchSize = prefetchSize>0?prefetchSize:PREFETCH_SIZE;
	}

	public String getServerHost() {
		return serverHost;
	}

	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVirtualHost() {
		return virtualHost;
	}

	public void setVirtualHost(String virtualHost) {
		this.virtualHost = virtualHost;
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public int getEventMsgProcessNum() {
		return eventMsgProcessNum;
	}

	public void setEventMsgProcessNum(int eventMsgProcessNum) {
		this.eventMsgProcessNum = eventMsgProcessNum;
	}

	public int getPrefetchSize() {
		return prefetchSize;
	}

	public void setPrefetchSize(int prefetchSize) {
		this.prefetchSize = prefetchSize;
	}
	
}
  
