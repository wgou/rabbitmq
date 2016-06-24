/**  
 * Project Name:retail-commons-rabbtimq  
 * File Name:HessionCodecFactory.java  
 * Package Name:com.retail.commons.rabbtimq.code  
 * Date:2016年5月2日上午11:51:37  
 * Copyright (c) 2016, 成都瑞泰尔科技有限公司 All Rights Reserved.  
 *  
*/  
  
package com.retail.commons.rabbitmq.code;  

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.retail.commons.rabbitmq.exception.DecodeException;
import com.retail.commons.rabbitmq.exception.EncodeException;

/**  
 * 描述: <br/>Hession 编码器 <br/>
 * ClassName:HessionCodecFactory <br/>  
 * Date:     2016年5月2日 上午11:51:37 <br/>  
 * @author   苟伟(gouewi@retail-tek.com) 
 * @version enclosing_typetags  
 * @see        
 */
public class HessianCodecFactory implements CodeFactory {
	
	//private Logger logger = Logger.getLogger(HessianCodecFactory.class);
	private final int buffer_size = 500*1024; // 默认字节大小
	
	public byte[] encode(Object obj) throws EncodeException {
		ByteArrayOutputStream os =null;
		HessianOutput output = null;
		  try {
			os = new ByteArrayOutputStream(buffer_size);
		    output = new HessianOutput(os);
			output.startCall();
			output.writeObject(obj);
			output.completeCall();
		} catch (IOException e) {
			throw new EncodeException("encode message IOException.",e);  
		}finally{
			if(output !=null)
				try {
					os.close();
				} catch (IOException e) {
					throw new EncodeException("Failed to close outputstream.",e);  
				}
		}
		 return os != null ? os.toByteArray() : null;
	}
	
	
	public Object decode(byte[] data) throws DecodeException {
		Object obj = null;
		ByteArrayInputStream bais = null;
		HessianInput input = null;
		try {
			bais = new ByteArrayInputStream(data);
			input = new HessianInput(bais);
			input.startReply();
			obj = input.readObject();
			input.completeReply();
		} catch (final IOException ex) {
			throw new DecodeException("message decode IOException.", ex);
		} catch (final Throwable e) {
			throw new DecodeException("message decode Throwable.", e);
		} finally {
			if (input != null) {
				try {
					bais.close();
				} catch (final IOException ex) {
					throw new DecodeException("Failed to close inpustream.", ex);
                }
            }
        }
        return obj;
	}

}
  
