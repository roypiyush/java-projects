package com.personal.thrift;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class FileServiceSyncServerMain {

    public static void main(String[] args) {
        try {
            FileServiceHandler handler = new FileServiceHandler();
            FileService.Processor<FileServiceHandler> processor = new com.personal.thrift.FileService.Processor<>(handler);
            tServerTransport(processor);
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    private static void tServerTransport(FileService.Processor<FileServiceHandler> processor) {
        try {
            TServerTransport serverTransport = new TServerSocket(9090);
            TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));

            System.out.println("Starting the FileServiceSync server...");
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
