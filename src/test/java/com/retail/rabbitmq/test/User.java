/**  
 * Project Name:retail-commons-rabbtimq  
 * File Name:User.java  
 * Package Name:com.retail.rabbtimq.test  
 * Date:2016年5月2日下午6:08:22  
 * Copyright (c) 2016, 成都瑞泰尔科技有限公司 All Rights Reserved.  
 *  
*/  
  
package com.retail.rabbitmq.test;  

import java.io.Serializable;
import java.util.List;

/**  
 * 描述: <br/>TODO; <br/>
 * ClassName:User <br/>  
 * Date:     2016年5月2日 下午6:08:22 <br/>  
 * @author   苟伟(gouewi@retail-tek.com) 
 * @version enclosing_typetags  
 * @see        
 */
public class User implements Serializable{

	
	/** 
	 * serialVersionUID:TODO TODO; 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int age;
	private List<Man> listMan;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	
	public List<Man> getListMan() {
		return listMan;
	}
	public void setListMan(List<Man> listMan) {
		this.listMan = listMan;
	}



	static class Man implements Serializable{
		/** 
		 * serialVersionUID:TODO TODO; 
		 */
		private static final long serialVersionUID = 1L;
		private String address;
		private String phone;
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		
	}
	
	public String toJson(){
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("name:").append(getName()).append(",");
		sb.append("age:").append(getAge()).append(",");
		sb.append("manList:[");
		for(Man m : getListMan()){
			sb.append("{");
			sb.append("address:").append(m.getAddress()).append(",");
			sb.append("phone:").append(m.getPhone()).append(",");
			sb.append("}");
		}
		sb.append("}");
		return sb.toString();
		
	}

}
  
