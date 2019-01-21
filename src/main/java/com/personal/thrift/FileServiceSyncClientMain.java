package com.personal.thrift;

import com.google.common.base.Stopwatch;
import org.apache.commons.io.IOUtils;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class FileServiceSyncClientMain {

    public static void main(String[] args) throws TException, IOException {

        TTransport transport = new TSocket("localhost", 9090);
        transport.open();
        FileService.Client client = new FileService.Client(new TBinaryProtocol(transport));
        String filename = args[0];
        byte[] data = IOUtils.toByteArray(new FileInputStream(new File(String.format("~/%s", filename).replaceAll("^~", System.getProperty("user.home")))));
        int product = client.sendFile(ByteBuffer.wrap(data), filename);
        System.out.println("Result " + product);
        transport.close();
    }

}