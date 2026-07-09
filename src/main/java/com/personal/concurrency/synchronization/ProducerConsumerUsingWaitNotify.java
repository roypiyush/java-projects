package com.personal.concurrency.synchronization;

public class ProducerConsumerUsingWaitNotify {

    private int data = 0;
    private int max = 10;
    private volatile boolean flag = false;

    private final Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {

        ProducerConsumerUsingWaitNotify starter = new ProducerConsumerUsingWaitNotify();
        Producer producer = starter.new Producer();
        Consumer consumer = starter.new Consumer();

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        long start = System.currentTimeMillis();
        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();
        System.out.println("Total Time taken : " + (System.currentTimeMillis() - start));
    }

    class Producer implements Runnable {
        @Override
        public void run() {

            while (data < max) {
                synchronized (lock) {
                    try {
                        while (flag) {
                            lock.wait();
                        }

                        data++;
                        System.out.println("Produced: " + data);
                        flag = true;
                        lock.notify();
                    } catch (InterruptedException e) {}
                }
            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {

            while (data < max) {
                synchronized (lock) {
                    try {
                        while (!flag) {
                            lock.wait();
                        }

                        System.out.println("Consumed: " + data);
                        flag = false;
                        lock.notify();
                    } catch (InterruptedException e) {}
                }
            }
        }
    }

}
