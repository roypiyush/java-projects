package com.personal.thrift;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;


@SuppressWarnings("unchecked")
public class MultiplicationAsyncServer {

    public static void main(String[] args) {
        try {
            MultiplicationHandler handler = new MultiplicationHandler();
            MultiplicationService.Processor processor = new MultiplicationService.Processor(handler);
            tNonBlockingServerTransport(processor);
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    private static void tNonBlockingServerTransport(MultiplicationService.Processor processor) {
        try {
            TNonblockingServerSocket socket = new TNonblockingServerSocket(9090);
            TNonblockingServer.Args args = new TNonblockingServer.Args(socket);
            args.processor(processor).
                    outputProtocolFactory(new TCompactProtocol.Factory()).
                    outputTransportFactory(new TFramedTransport.Factory()).
                    inputProtocolFactory(new TCompactProtocol.Factory()).
                    inputTransportFactory(new TFramedTransport.Factory());

            TNonblockingServer nonblockingServer = new TNonblockingServer(args);
            System.out.println("Starting the tServerTransport server...");
            nonblockingServer.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
