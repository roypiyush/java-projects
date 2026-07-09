package com.personal.thrift;

import java.nio.ByteBuffer;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

// Generated
public class MultiplicationClient {

    public static void main(String[] args) throws Exception {
        TTransport transport = new TSocket("localhost", 9090);
        transport.open();

        TProtocol protocol = new TBinaryProtocol(transport);

        TMultiplexedProtocol multProtocol = new TMultiplexedProtocol(protocol, "MultiplicationService");
        MultiplicationService.Client multClient = new MultiplicationService.Client(multProtocol);

        System.out.printf("Multiplication %s\n", multClient.multiply(3, 5));

        TMultiplexedProtocol fileProtocol = new TMultiplexedProtocol(protocol, "FileService");
        FileService.Client fileClient = new FileService.Client(fileProtocol);

        ByteBuffer fileData = ByteBuffer.wrap("Hello Thrift".getBytes());
        fileClient.sendFile(fileData, "testing.txt");

        ByteBuffer downloaded = fileClient.downloadFile("test.txt", 0, 5);
        byte[] bytes = new byte[downloaded.remaining()];
        downloaded.get(bytes);
        System.out.printf("Downloaded: %s\n", new String(bytes));

        transport.close();
    }
}
