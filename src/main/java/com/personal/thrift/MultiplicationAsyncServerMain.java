package com.personal.thrift;

import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;


@SuppressWarnings("unchecked")
public class MultiplicationAsyncServerMain {

    public static void main(String[] args) {
        try {
            MultiplicationHandler handler = new MultiplicationHandler();
            MultiplicationService.AsyncProcessor processor = new MultiplicationService.AsyncProcessor<>(handler);
            tNonBlockingServerTransport(processor);
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    private static void tNonBlockingServerTransport(MultiplicationService.AsyncProcessor processor) {
        try {
            TNonblockingServerTransport tNonblockingServerTransport = new TNonblockingServerSocket(9090);
            TNonblockingServer nonblockingServer = new TNonblockingServer(new TNonblockingServer
                    .Args(tNonblockingServerTransport)
                    .processor(processor));
            System.out.println("Starting the tServerTransport server...");
            nonblockingServer.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
