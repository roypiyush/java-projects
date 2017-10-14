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


        TFramedTransport transport = new TFramedTransport(new TSocket("localhost", 9090));
        TProtocol protocol = new TCompactProtocol.Factory().getProtocol(transport);


        final List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        MultiplicationService.Client client = new MultiplicationService.Client(protocol);
                        transport.open();
                        int result = client.multiply(threads.size() + 1, 5);
                        System.out.println(String.format("result=%d", result));
                        transport.close();
                    } catch (TException e) {
                        e.printStackTrace();
                    }
                }
            });
            threads.add(t);
        }
        for (int i = 0; i < 100; i++) {
            threads.get(i).start();
        }


    }

}