package com.personal.tricks;

import com.personal.model.MySimpleJavaBean;

class MainMethodTest {

	/**
	 * @param args
	 * @throws CloneNotSupportedException 
	 */
	public static void main(String[] args) throws CloneNotSupportedException {
		
		MySimpleJavaBean bean = new MySimpleJavaBean();
		bean.setName("Piyush");
		bean.setValue("Priceless");
		
		System.out.println(bean);
		Object clone = bean.clone();
		System.out.println(clone);

	}

}
