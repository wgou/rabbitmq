/**  
 * Project Name:retail-commons-rabbtimq  
 * File Name:MessageAdapterHandler.java  
 * Package Name:com.retail.commons.rabbtimq  
 * Date:2016年5月2日下午1:27:33  
 * Copyright (c) 2016, 成都瑞泰尔科技有限公司 All Rights Reserved.  
 *  
*/  
  
package com.retail.commons.rabbitmq;  

import java.util.LinkedList;

import org.apache.log4j.Logger;

import com.retail.commons.rabbitmq.code.CodeFactory;
import com.retail.commons.rabbitmq.exception.DecodeException;
import com.retail.commons.rabbitmq.exception.EncodeException;

/**  
 * 描述: <br/>消息处理handler 链表 <br/>
 * ClassName:MessageAdapterHandler <br/>  
 * Date:     2016年5月2日 下午1:27:33 <br/>  
 * @author   苟伟(gouewi@retail-tek.com) 
 * @version enclosing_typetags  
 * @see        
 */
public class MessageAdapterHandler {

	private Logger logger = Logger.getLogger(MessageAdapterHandler.class);
	//消息解码器处理链表
	private  LinkedList<EventProcessorDecode> decodeList;
	//消息编码器处理链表
	private  LinkedList<EventProcessorEncode> encodeList;
	//消息handler处理链表
	private LinkedList<EventProcesser> handlerList ;
	public MessageAdapterHandler(){
		this.decodeList = new LinkedList<EventProcessorDecode>();
		this.encodeList = new LinkedList<EventProcessorEncode>();
		this.handlerList=new LinkedList<EventProcesser>();
	}
	/**
	 * add:添加一个解码器 <br/>  
	 * @author luochen  
	 * @param eventProcesser
	 * @param codeFactory
	 */
	public void addDecode(CodeFactory codeFactory){
		if (codeFactory == null) {
			throw new RuntimeException("codecFactory can not be null. ");
		}
		decodeList.addLast(new EventProcessorDecode(codeFactory));
		logger.info("添加解码器 -> "+codeFactory.getClass().getName());
	}
	
	/**
	 * add:添加一个编码器 <br/>  
	 * @author luochen  
	 * @param eventProcesser
	 * @param codeFactory
	 */
	public void addEncode(CodeFactory codeFactory){
		if (codeFactory == null) {
			throw new RuntimeException("codecFactory can not be null. ");
		}
		encodeList.addLast(new EventProcessorEncode(codeFactory));
		logger.info("添加编码器 ->"+codeFactory.getClass().getName());
	}
	
	/**
	 * 添加一个消息处理Handler
	 * @author luochen  
	 * @param processor
	 * @return 
	 */
	public void addHandler(EventProcesser processor){
		if (processor == null) {
			throw new RuntimeException("processor can not be null. ");
		}
		handlerList.addLast(processor);
	}
	
	
	/**
	 * 描述:<br/>消息解码处理<br/>  
	 * ClassName: EventProcessorWrap <br/>  
	 * date: 2016年5月2日 下午4:28:34 <br/>  
	 * @author  苟伟(gouewi@retail-tek.com)   
	 * @version MessageAdapterHandler
	 */
	protected class EventProcessorDecode{
		
		private CodeFactory codeFactory;
		
		EventProcessorDecode(CodeFactory codeFactory){
			this.codeFactory=codeFactory;
		}
		
		public Object process(byte[] data) throws DecodeException{
			Object e = codeFactory.decode(data);
			return e;
		}
		
	}
	
	/**
	 * 描述:<br/>消息编码处理<br/>  
	 * ClassName: EventProcessorWrap <br/>  
	 * date: 2016年5月2日 下午4:28:34 <br/>  
	 * @author  苟伟(gouewi@retail-tek.com)   
	 * @version MessageAdapterHandler
	 */
	protected class EventProcessorEncode{
		
		private CodeFactory codeFactory;
		
		EventProcessorEncode(CodeFactory codeFactory){
			this.codeFactory=codeFactory;
		}
		
		public byte[] process(Object data) throws EncodeException{
			byte[] e = codeFactory.encode(data);
			return e;
		}
	}

	public LinkedList<EventProcessorDecode> getDecodeList() {
		return decodeList;
	}
	public void setDecodeList(LinkedList<EventProcessorDecode> decodeList) {
		this.decodeList = decodeList;
	}
	public LinkedList<EventProcessorEncode> getEncodeList() {
		return encodeList;
	}
	public void setEncodeList(LinkedList<EventProcessorEncode> encodeList) {
		this.encodeList = encodeList;
	}
	public LinkedList<EventProcesser> getHandlerList() {
		return handlerList;
	}
	public void setHandlerList(LinkedList<EventProcesser> handlerList) {
		this.handlerList = handlerList;
	}
	
	
}
  
