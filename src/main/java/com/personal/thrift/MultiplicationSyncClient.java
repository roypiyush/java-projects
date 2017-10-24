package com.personal.thrift;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;

import java.util.ArrayList;
import java.util.List;

public class MultiplicationSyncClient {

    public static void main(String[] args) {

        class RunnableJob implements Runnable {
            private int id;

            public RunnableJob (int id) {
                this.id = id;
            }

            public void run() {
                try {
                    TFramedTransport transport = new TFramedTransport(new TSocket("localhost", 9090));
                    TProtocol protocol = new TCompactProtocol.Factory().getProtocol(transport);
                    MultiplicationService.Client client = new MultiplicationService.Client(protocol);
                    transport.open();
                    int result = client.multiply(id + 1, 5);
                    System.out.println(String.format("result=%d", result));
                    transport.close();
                } catch (TException e) {
                    e.printStackTrace();
                }
            }
        }

        final List<RunnableJob> runnables = new ArrayList<>();
        int thread_size = 100000;
        for (int i = 0; i < thread_size; i++) {
            runnables.add(new RunnableJob(i));
        }
        for (int i = 0; i < thread_size; i++) {
            new Thread(runnables.get(i)).start();
        }


    }

}