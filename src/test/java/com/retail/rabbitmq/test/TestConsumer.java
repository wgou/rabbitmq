/**  
 * Project Name:retail-commons-rabbtimq  
 * File Name:TestConsumer.java  
 * Package Name:com.retail.rabbtimq.test  
 * Date:2016年5月2日下午6:03:27  
 * Copyright (c) 2016, 成都瑞泰尔科技有限公司 All Rights Reserved.  
 *  
*/  
  
package com.retail.rabbitmq.test;  

import com.retail.commons.rabbitmq.EventConfig;
import com.retail.commons.rabbitmq.EventProcesser;
import com.retail.commons.rabbitmq.code.HessianCodecFactory;
import com.retail.commons.rabbitmq.consumer.ConsumerEventController;

/**  
 * 描述: <br/>TODO; <br/>
 * ClassName:TestConsumer <br/>  
 * Date:     2016年5月2日 下午6:03:27 <br/>  
 * @author   苟伟(gouewi@retail-tek.com) 
 * @version enclosing_typetags  
 * @see        
 */
public class TestConsumer {

	public static void main(String[] args) {
		EventConfig config = new EventConfig("192.168.163.134");
		config.setPort(5673);
		config.setUsername("admin");
		config.setPassword("123456");
		config.setVirtualHost("/");
		ConsumerEventController consumer = new ConsumerEventController(config);
		consumer.setQueueAndExchange("testQueue", null);
		//consumer.addDecode(new StringCodeFactory());
		consumer.addDecode(new HessianCodecFactory());
		consumer.addMessageHandler(new EventProcesser() {
			
			public Object process(Object e) {
				User u = (User)e;
				System.out.println(u.toJson());
				return e;
			}
		});
		consumer.start();
		 
	}
}
  
