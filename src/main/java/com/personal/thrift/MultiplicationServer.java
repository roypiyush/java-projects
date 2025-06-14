package com.personal.thrift;

import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

// Generated
public class MultiplicationServer {

    public static void main(String[] args) {
        try {
            MultiplicationService.Processor<MultiplicationService.Iface> multProcessor = new MultiplicationService.Processor<>(
                    new MultiplicationServiceHandler());

            FileService.Processor<FileService.Iface> fileProcessor = new FileService.Processor<>(
                    new FileServiceHandler());

            TServerTransport serverTransport = new TServerSocket(9090);

            // Combine services using TMultiplexedProcessor
            org.apache.thrift.TMultiplexedProcessor multiplexedProcessor = new org.apache.thrift.TMultiplexedProcessor();
            multiplexedProcessor.registerProcessor("MultiplicationService", multProcessor);
            multiplexedProcessor.registerProcessor("FileService", fileProcessor);

            TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(multiplexedProcessor));

            System.out.println("Starting the server...");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
