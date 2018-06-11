package com.personal.old.designpatterns.singleton;

public class BillPughSingleton {

	private BillPughSingleton() {}
	
	private static class SingletonHelper {
		private static final BillPughSingleton SINGLETON = new BillPughSingleton(); 
	}
	
	public static BillPughSingleton getInstance() {
		return SingletonHelper.SINGLETON;
	}
	
	public static void main(String[] args) {
		
		BillPughSingleton instance1 = BillPughSingleton.getInstance();
		BillPughSingleton instance2 = BillPughSingleton.getInstance();
		
		if(instance1.hashCode() == instance2.hashCode()) {
			System.out.println("Hey, instances are same.");
		}
		else {
			System.out.println("Instances are NOT same.");
		}
		
	}

}
