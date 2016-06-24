/**  
 * Project Name:retail-commons-rabbtimq  
 * File Name:EventConnection.java  
 * Package Name:com.retail.commons.rabbtimq  
 * Date:2016年5月2日下午1:05:14  
 * Copyright (c) 2016, 成都瑞泰尔科技有限公司 All Rights Reserved.  
 *  
*/  
  
package com.retail.commons.rabbitmq;  



/**  
 * 描述: <br/>RabbtiMQ 控制器 <br/>
 * ClassName:EventConnection <br/>  
 * Date:     2016年5月2日 下午1:05:14 <br/>  
 * @author   苟伟(gouewi@retail-tek.com) 
 * @version enclosing_typetags  
 * @see        
 */
public interface EventController {
 
	/**
	 * 控制器启动方法
	 */
	void start();
	
	/**
	 * 绑定消息处理事件
	 */
	void addMessageHandler(EventProcesser eventProcesser);
	
}
  
