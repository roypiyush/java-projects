package com.personal.references;

import java.util.WeakHashMap;

import com.personal.model.MySimpleJavaBean;

public class WeakReferenceMapDemo {

	private static WeakHashMap<String, MySimpleJavaBean> hashMap;
	
	public static void main(String[] args) {
		hashMap = new WeakHashMap<String, MySimpleJavaBean>(10000);
		
		int i = 0;
        while (true) {
        	++i;
            hashMap.put("String" + Integer.toString(i), new MySimpleJavaBean());
            System.out.println("im still working!!!!" + hashMap.size());
            
        }
	}
}
