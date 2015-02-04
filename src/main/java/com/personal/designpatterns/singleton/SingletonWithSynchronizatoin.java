package com.personal.designpatterns.singleton;

public class SingletonWithSynchronizatoin {

	private static volatile SingletonWithSynchronizatoin INSTANCE = null;
	
	private SingletonWithSynchronizatoin(){}
	
	public static SingletonWithSynchronizatoin getInstance() {
		if(INSTANCE == null) {
			synchronized (SingletonWithSynchronizatoin.class) {
				if(INSTANCE == null) {
					INSTANCE = new SingletonWithSynchronizatoin();
				}
			}
		}
		return INSTANCE;
	}
	
	
	public static void main(String[] args) {
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(SingletonWithSynchronizatoin.getInstance().hashCode());
				
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(SingletonWithSynchronizatoin.getInstance().hashCode());
				
			}
		});
		t1.start();
		t2.start();

	}

}
