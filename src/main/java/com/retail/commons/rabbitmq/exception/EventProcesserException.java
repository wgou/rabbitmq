/**  
 * Project Name:retail-commons-rabbitmq  
 * File Name:EventProcesserException.java  
 * Package Name:com.retail.commons.rabbitmq.exception  
 * Date:2016年5月10日下午2:24:48  
 * Copyright (c) 2016, 成都瑞泰尔科技有限公司 All Rights Reserved.  
 *  
 */
package com.retail.commons.rabbitmq.exception;

/**  
 * 描述:<br/>消息处理异常 <br/>  
 * ClassName: EventProcesserException <br/>  
 * date: 2016年5月10日 下午2:24:48 <br/>  
 * @author  苟伟(gouewi@retail-tek.com)   
 * @version   
 */
public class EventProcesserException extends Exception{

	/** 
	 * serialVersionUID:TODO TODO; 
	 */
	private static final long serialVersionUID = 1L;
	
	public EventProcesserException(){
		super();
	}
	public EventProcesserException(String message) {
        super(message);
    }
	public EventProcesserException(String message,Throwable cause) {
        super(message,cause);
    }
}
