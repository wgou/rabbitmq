/**  
 * Project Name:retail-commons-rabbtimq  
 * File Name:ConsumerException.java  
 * Package Name:com.retail.commons.rabbtimq.excetion  
 * Date:2016年5月2日下午6:55:06  
 * Copyright (c) 2016, 成都瑞泰尔科技有限公司 All Rights Reserved.  
 *  
*/  
  
package com.retail.commons.rabbitmq.exception;  
/**  
 * 描述: <br/>消费者在消费消息时发生异常 <br/>
 * ClassName:ConsumerException <br/>  
 * Date:     2016年5月2日 下午6:55:06 <br/>  
 * @author   苟伟(gouewi@retail-tek.com) 
 * @version enclosing_typetags  
 * @see        
 */
public class ConsumerException extends Exception {

	/** 
	 * serialVersionUID:TODO TODO; 
	 */
	private static final long serialVersionUID = -3232107800080223236L;
	public ConsumerException(Throwable cause){
		super(cause);
	}
	 public ConsumerException(String message, Throwable cause) {
        super(message, cause);
    }
}
  
