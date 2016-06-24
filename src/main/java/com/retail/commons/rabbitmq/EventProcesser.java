/**  
 * Project Name:retail-commons-rabbtimq  
 * File Name:EventProcesser.java  
 * Package Name:com.retail.commons.rabbtimq  
 * Date:2016年5月2日下午1:30:46  
 * Copyright (c) 2016, 成都瑞泰尔科技有限公司 All Rights Reserved.  
 *  
*/  
  
package com.retail.commons.rabbitmq;  

import com.retail.commons.rabbitmq.exception.EventProcesserException;

/**  
 * 描述: <br/>消息处理接口 <br/>
 * ClassName:EventProcesser <br/>  
 * Date:     2016年5月2日 下午1:30:46 <br/>  
 * @author   苟伟(gouewi@retail-tek.com) 
 * @version enclosing_typetags  
 * @see        
 */
public interface EventProcesser {

	public Object process(Object e) throws EventProcesserException;
}
  
