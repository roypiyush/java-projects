package com.personal.thrift;

import org.apache.commons.io.IOUtils;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.io.*;
import java.nio.ByteBuffer;

public class FileServiceSyncClientMain {

    public static void main(String[] args) throws TException, IOException {

        TTransport transport = new TSocket("localhost", 9090);
        transport.open();
        com.personal.thrift.FileService.Client client = new com.personal.thrift.FileService.Client(new TBinaryProtocol(transport));
        String filename = args[0];
        sendFile(client, filename);
        downloadFile(client, filename);
        transport.close();
    }

    private static void downloadFile(com.personal.thrift.FileService.Client client, String filename) throws TException, FileNotFoundException, IOException {
        int offset = 0;
        int length = 1024;
        RandomAccessFile randomAccessFile = new RandomAccessFile(new File(String.format("/tmp/downloaded_%s", filename)) ,"rw");
        randomAccessFile.seek(offset);
        int i = 0;
        while (true) {
            ByteBuffer rawData = client.downloadFile(System.getProperty("user.home") + File.separator + filename, offset, length);
            if (rawData.capacity() == 0) {
                break;
            }
            randomAccessFile.write(rawData.array());
            offset = offset + length;
            i++;
        }
        randomAccessFile.close();
    }

    private static void sendFile(com.personal.thrift.FileService.Client client, String filename) throws IOException, TException {
        byte[] data = IOUtils.toByteArray(new FileInputStream(new File(String.format("~/%s", filename).replaceAll("^~", System.getProperty("user.home")))));
        client.sendFile(ByteBuffer.wrap(data), filename);
    }

}