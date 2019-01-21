package com.personal.thrift;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

@SuppressWarnings("unchecked")
public class MultiplicationSimpleServerMain {

    public static void main(String[] args) {
        try {
            MultiplicationHandler handler = new MultiplicationHandler();
            MultiplicationService.Processor processor = new MultiplicationService.Processor(handler);
            tServerTransport(processor);
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    private static void tServerTransport(MultiplicationService.Processor processor) {
        try {
            TServerTransport serverTransport = new TServerSocket(9090);
            TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));

            System.out.println("Starting the tServerTransport server...");
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Stopping Server...");
                server.stop();
                System.out.println("Server Stopped");
            }));
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
