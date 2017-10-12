package com.personal.thrift;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class MultiplicationServer {

    public static void main(String[] args) {
        try {
            MultiplicationHandler handler = new MultiplicationHandler();
            MultiplicationService.Processor processor = new MultiplicationService.Processor(handler);
            // Thread Initialized
            new Thread(() -> simple(processor)).start();
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    public static void simple(MultiplicationService.Processor processor) {
        try {
            TServerTransport serverTransport = new TServerSocket(9090);
            TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));

            System.out.println("Starting the simple server...");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
