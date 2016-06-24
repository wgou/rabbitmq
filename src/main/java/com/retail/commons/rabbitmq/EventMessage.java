/**  
 * Project Name:retail-commons-rabbtimq  
 * File Name:EventMessage.java  
 * Package Name:com.retail.commons.rabbtimq  
 * Date:2016年5月2日下午1:42:09  
 * Copyright (c) 2016, 成都瑞泰尔科技有限公司 All Rights Reserved.  
 *  
*/  
  
package com.retail.commons.rabbitmq;  
/**  
 * 描述: <br/>构造一个消息对象 <br/>
 * ClassName:EventMessage <br/>  
 * Date:     2016年5月2日 下午1:42:09 <br/>  
 * @author   苟伟(gouewi@retail-tek.com) 
 * @version enclosing_typetags  
 * @see        
 */
public class EventMessage {

	private String queueName;
	private String exchangeName;
	private byte[] eventData;
	public String getQueueName() {
		return queueName;
	}
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
	public String getExchangeName() {
		return exchangeName;
	}
	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}
	public byte[] getEventData() {
		return eventData;
	}
	public void setEventData(byte[] eventData) {
		this.eventData = eventData;
	}
	
}
  
