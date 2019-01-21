package com.personal.thrift;

import com.google.common.base.Stopwatch;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiplicationSyncClientMain {

    public static void main(String[] args) throws InterruptedException {

        final AtomicInteger count = new AtomicInteger(0);
        class RunnableJob implements Runnable {
            private int id;
            private final AtomicInteger count;
            public RunnableJob (int id, final AtomicInteger count) {
                this.id = id;
                this.count = count;
            }

            public void run() {
                try {
                    Stopwatch stopwatch = Stopwatch.createStarted();
                    TTransport transport;

                    transport = new TSocket("localhost", 9090);
                    transport.open();

                    TProtocol protocol = new TBinaryProtocol(transport);
                    MultiplicationService.Client client = new MultiplicationService.Client(protocol);

                    int product = client.multiply(id + 1, 5);
                    System.out.println("3*5=" + product);

                    transport.close();
                    final long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
                    System.out.println("Elapsed Time in millis " + elapsed);
                    if (elapsed > 1000) {
                        count.incrementAndGet();
                    }
                } catch (TException e) {
                    e.printStackTrace();
                }
            }
        }

        final List<RunnableJob> runnables = new ArrayList<>();
        int thread_size = 10000;
        for (int i = 0; i < thread_size; i++) {
            runnables.add(new RunnableJob(i, count));
        }
        for (int i = 0; i < thread_size; i++) {
            new Thread(runnables.get(i)).start();
        }
        Thread.currentThread().join();
        System.out.println("Final count " + count.get());
    }

}