/**  
 * Project Name:retail-commons-rabbitmq  
 * File Name:EncodeException.java  
 * Package Name:com.retail.commons.rabbitmq.exception  
 * Date:2016年5月10日下午2:31:53  
 * Copyright (c) 2016, 成都瑞泰尔科技有限公司 All Rights Reserved.  
 *  
 */
package com.retail.commons.rabbitmq.exception;

/**  
 * 描述:<br/>消息编码发生异常 <br/>  
 * ClassName: EncodeException <br/>  
 * date: 2016年5月10日 下午2:31:53 <br/>  
 * @author  苟伟(gouewi@retail-tek.com)   
 * @version   
 */
public class EncodeException extends Exception{

	/** 
	 * serialVersionUID:TODO TODO; 
	 */
	private static final long serialVersionUID = 1L;
	public EncodeException(){
		super();
	}
	public EncodeException(String message) {
        super(message);
    }
	public EncodeException(String message,Throwable cause) {
        super(message,cause);
    }
	


}
