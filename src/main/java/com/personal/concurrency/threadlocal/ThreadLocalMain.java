package com.personal.concurrency.threadlocal;

import java.util.concurrent.atomic.AtomicInteger;

import com.personal.designpatterns.singleton.BillPughSingleton;

class ThreadId {
    // Atomic integer containing the next thread ID to be assigned
    private static final AtomicInteger nextId = new AtomicInteger(0);

    // Thread local variable containing each thread's ID
    private static final ThreadLocal<Integer> threadId =
        new ThreadLocal<Integer>() {
            @Override protected Integer initialValue() {
                return nextId.getAndIncrement();
        }
    };

    // Returns the current thread's unique ID, assigning it if necessary
    public static int get() {
        return threadId.get();
    }
    
    private static final ThreadLocal<BillPughSingleton> THREAD_LOCAL_SINGLETON = new ThreadLocal<BillPughSingleton>(){
        @Override protected BillPughSingleton initialValue() {
            return BillPughSingleton.getInstance();
    }
};
    
    public static BillPughSingleton getSingleton() {
    	return THREAD_LOCAL_SINGLETON.get();
    }
}

public class ThreadLocalMain {

	public static void main(String[] args) throws InterruptedException {
		
		for(int i = 0; i < 5; i++) {
			Thread thread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					System.out.println(ThreadId.get());
					
				}
			});
			thread.start();
			thread.join();
		}
	
		for(int i = 0; i < 5; i++) {
			Thread thread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					System.out.println(ThreadId.getSingleton().hashCode());
					
				}
			});
			thread.start();
			thread.join();
		}
		
		
		
	}

}
