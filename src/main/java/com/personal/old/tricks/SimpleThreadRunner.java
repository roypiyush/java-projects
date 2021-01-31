package com.personal.old.tricks;

public class SimpleThreadRunner {
    private static final int THREAD_RUN_MULTIPLIER = 5;
    private static final int THREAD_WAIT_IN_MILLIS = 1000;

    public static void main(String[] args) {
        int size = args.length == 0 ? THREAD_RUN_MULTIPLIER : Integer.parseInt(args[0]);
        new Thread(new MyRunner(size), "Thread-1").start();
        new Thread(new MyRunner(size), "Thread-2").start();
    }

    static class MyRunner implements Runnable {
        private final int size;
        public MyRunner (int size) {
            this.size = size;
        }

        @Override
        public void run() {
            int count = 0;
            while (count < size) {
                count++;
                try {
                    System.out.printf("Running in %s\n", Thread.currentThread().getName());
                    Thread.sleep(THREAD_WAIT_IN_MILLIS);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
