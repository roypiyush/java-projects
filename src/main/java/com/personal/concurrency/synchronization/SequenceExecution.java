package com.personal.concurrency.synchronization;

public class SequenceExecution {

    private final Object lock = new Object();
    static volatile int iterationNumber = 0;
    static volatile int counter = 1;

    static class CounterIncrementer implements Runnable {
        private final int number;
        private final Object lock;

        public CounterIncrementer(Object lock, int number) {
            this.lock = lock;
            this.number = number;
        }

        @Override
        public void run() {
            try {
                synchronized (this.lock) {
                    while (iterationNumber < 9) {
                        while (iterationNumber * 10 + number != counter) {
                            this.lock.wait();
                        }
                        System.out.println("Running -> " + Thread.currentThread().getName() + " " + counter);

                        if (counter % 10 == 0) {
                            iterationNumber++;
                        }
                        counter++;
                        this.lock.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("Error " + Thread.currentThread().getName() + " " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SequenceExecution sequenceExecution = new SequenceExecution();

        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new CounterIncrementer(sequenceExecution.lock, i + 1), "Thread-" + (i + 1));
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Error " + Thread.currentThread().getName() + " " + e.getMessage());
            }
        }

        System.out.println("Exiting Main...");
    }
}
