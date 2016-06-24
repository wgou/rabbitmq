/**  
 * Project Name:retail-commons-rabbtimq  
 * File Name:StringCodeFactory.java  
 * Package Name:com.retail.commons.rabbtimq.code  
 * Date:2016年5月2日下午5:57:18  
 * Copyright (c) 2016, 成都瑞泰尔科技有限公司 All Rights Reserved.  
 *  
*/  
  
package com.retail.commons.rabbitmq.code;  

import java.io.UnsupportedEncodingException;

import com.retail.commons.rabbitmq.exception.DecodeException;
import com.retail.commons.rabbitmq.exception.EncodeException;

/**  
 * 描述: <br/>字符串编码器<br/>
 * ClassName:StringCodeFactory <br/>  
 * Date:     2016年5月2日 下午5:57:18 <br/>  
 * @author   苟伟(gouewi@retail-tek.com) 
 * @version enclosing_typetags  
 * @see        
 */
public class StringCodeFactory implements CodeFactory{

	public byte[] encode(Object obj) throws EncodeException {
		byte[] data = null;
		try {
			data = ((String)obj).getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new EncodeException("encode message UnsupportedEncodingException.", e) ;
		}
		return data;
	}

	public Object decode(byte[] data) throws DecodeException {
		Object o = null;
		try {
			o = new String(data,"utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new DecodeException("decode message UnsupportedEncodingException.", e) ;
		}
		return o;
	}

}
  
