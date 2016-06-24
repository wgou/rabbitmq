/**  
 * Project Name:retail-commons-rabbitmq  
 * File Name:DecodeException.java  
 * Package Name:com.retail.commons.rabbitmq.exception  
 * Date:2016年5月10日下午2:23:56  
 * Copyright (c) 2016, 成都瑞泰尔科技有限公司 All Rights Reserved.  
 *  
 */
package com.retail.commons.rabbitmq.exception;

/**  
 * 描述:<br/>消息解码发生异常 <br/>  
 * ClassName: DecodeException <br/>  
 * date: 2016年5月10日 下午2:23:56 <br/>  
 * @author  苟伟(gouewi@retail-tek.com)   
 * @version   
 */
public class DecodeException extends Exception{

	/** 
	 * serialVersionUID:TODO TODO; 
	 */
	private static final long serialVersionUID = 1L;
	public DecodeException(){
		super();
	}
	public DecodeException(String message) {
        super(message);
    }
	public DecodeException(String message,Throwable cause) {
        super(message,cause);
    }
}
