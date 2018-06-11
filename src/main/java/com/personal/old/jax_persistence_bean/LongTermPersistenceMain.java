package com.personal.old.jax_persistence_bean;

import java.beans.XMLEncoder;

import com.personal.old.model.MySimpleJavaBean;

public class LongTermPersistenceMain {

	public static void main(String[] args) {
		XMLEncoder encoder = null;
		
		try {
			// Encoding Object
			encoder = new XMLEncoder(System.out);
			MySimpleJavaBean bean = new MySimpleJavaBean();
			bean.setName("Piyush");
			bean.setValue("Priceless");
			encoder.writeObject(bean);
			
			// Use XMLDecoder to decode an object
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(encoder != null) encoder.close();
		}
	}

}
