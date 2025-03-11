package com.personal.old.tricks;

import com.personal.old.model.MySimpleJavaBean;

import java.util.TreeMap;
import java.util.TreeSet;

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


		TreeMap<Integer, Integer> map = new TreeMap<>();
		map.put(3, 3);
		map.put(2, 2);
		map.put(1, 1);
		System.out.println(map.firstKey());
		System.out.println(map.lastKey());

		TreeSet<Integer> set = new TreeSet<>();
		set.add(3);
		set.add(2);
		set.add(1);

		System.out.println(set.pollFirst());
		System.out.println(set.pollLast());

	}

}
