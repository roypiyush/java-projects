package com.personal.old.tricks;

import com.personal.old.model.MySimpleJavaBean;

class CloneMain {

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
