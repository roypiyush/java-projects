package com.personal.concurrency.executorframework;


import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class MultiThreadedScheduledService {
    public static void main(String[] args) throws InterruptedException {

        final AtomicLong timeInstant = new AtomicLong(System.currentTimeMillis());
        final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4, getThreadFactory());

        final ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(() -> {
            final AtomicLong currentTime = new AtomicLong(System.currentTimeMillis());
            final long diff = currentTime.longValue() - timeInstant.longValue();

            System.out.println("Running Scheduled Thread -> " + Thread.currentThread().getName() + " " + diff);

            int randomTaskCount = new Random().nextInt(8);
            while (!Thread.interrupted() && randomTaskCount-- > 0) {
                scheduledExecutorService.submit(randomTask());
            }
            timeInstant.set(System.currentTimeMillis());
        }, 0, 2, TimeUnit.SECONDS);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            scheduledExecutorService.shutdown();
            System.out.println("Shutting down...");
            scheduledFuture.cancel(true);
            while (!scheduledExecutorService.isTerminated()) {}
        }));

        scheduledExecutorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        System.out.println("Stopped!");
    }

    public static Runnable randomTask() {
        return () -> System.out.println("Executing Random task " + UUID.randomUUID());
    }

    private static ThreadFactory getThreadFactory() {
        return r -> new Thread(r);
    }
}
