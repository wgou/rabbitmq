/**  
 * Project Name:retail-commons-rabbtimq  
 * File Name:TestProduct.java  
 * Package Name:com.retail.rabbtimq.test  
 * Date:2016年5月2日下午5:48:25  
 * Copyright (c) 2016, 成都瑞泰尔科技有限公司 All Rights Reserved.  
 *  
*/  
  
package com.retail.rabbitmq.test;  

import java.util.ArrayList;
import java.util.List;

import com.retail.commons.rabbitmq.EventConfig;
import com.retail.commons.rabbitmq.code.HessianCodecFactory;
import com.retail.commons.rabbitmq.product.ProductEventController;
import com.retail.rabbitmq.test.User.Man;

/**  
 * 描述: <br/>TODO; <br/>
 * ClassName:TestProduct <br/>  
 * Date:     2016年5月2日 下午5:48:25 <br/>  
 * @author   苟伟(gouewi@retail-tek.com) 
 * @version enclosing_typetags  
 * @see        
 */
public class TestProduct {

	public static void main(String[] args) {
		EventConfig config = new EventConfig("192.168.163.134");
		config.setPort(5673);
		config.setUsername("admin");
		config.setPassword("123456");
		config.setVirtualHost("/");
		ProductEventController product = new ProductEventController(config);
		product.setQueueAndExchange("testQueue", null);
		//product.addEncode(new StringCodeFactory()); //字符串编码器
		product.addEncode(new HessianCodecFactory());  //hessian 序列化
		product.start();
		for(int i = 0;i<100;i++)
			try {
				User u = new User();
				u.setName("zhangsan" +i);
				u.setAge(i + 10);
				List<Man> list = new ArrayList<Man>();
				for(int j=0;j<5;j++){
					Man man = new Man();
					man.setAddress("成都-" + j);
					man.setPhone("1234" +i);
					list.add(man);
				}
				u.setListMan(list);
				product.send(u);
				//TimeUnit.SECONDS.sleep(1);
			} catch (Exception e) {
				e.printStackTrace();  
				
			}
	}
	
}
  
