package com.retail.commons.rabbitmq.code;

import java.io.IOException;

import com.retail.commons.rabbitmq.exception.DecodeException;
import com.retail.commons.rabbitmq.exception.EncodeException;

/**
 * 描述:<br/>编码和解码工厂接口<br/>  
 * ClassName: CodeFactory <br/>  
 * date: 2016年5月2日 上午11:48:10 <br/>  
 * @author  苟伟(gouewi@retail-tek.com)   
 * @version
 */
public interface CodeFactory {
	/**
	 * encode:消息编码接口. <br/>  
	 * @author luochen  
	 * @param obj
	 * @return
	 * @throws IOException
	 */
	byte[] encode(Object obj) throws EncodeException;
	/**
	 * decode:消息解码接口. <br/>  
	 * @author luochen  
	 * @param data
	 * @return
	 * @throws IOException
	 */
	Object decode(byte[] data) throws DecodeException;
}
