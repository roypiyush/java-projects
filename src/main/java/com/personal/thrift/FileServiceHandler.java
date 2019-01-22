package com.personal.thrift;

import org.apache.commons.io.IOUtils;
import org.apache.thrift.TException;

import java.io.*;
import java.nio.ByteBuffer;

public class FileServiceHandler implements com.personal.thrift.FileService.Iface {
    @Override
    public String sendFile(ByteBuffer fileData, String name) throws TException {
        byte[] data = fileData.array();
        System.out.println(String.format("Received File %s of size %s", name, data.length));
        try {
            IOUtils.write(data, new FileOutputStream("/tmp/" + name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.toString(0);
    }

    @Override
    public ByteBuffer downloadFile(String filePath, int offset, int length) throws TException {
        File file = new File(filePath);
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            long totalLength = randomAccessFile.length();
            byte[] dataRead = new byte[1024];
            if (offset >= totalLength) {
                return null;
            }
            if (offset + length > totalLength) {
                length = (int) totalLength - (offset + length);
            }
            randomAccessFile.seek(offset);
            int bytesRead = randomAccessFile.read(dataRead);
            randomAccessFile.close();
            if (bytesRead < dataRead.length) {
                byte[] bytes = new byte[bytesRead];
                for (int i = 0; i < bytesRead; i++) {
                    bytes[i] = dataRead[i];
                }
                return ByteBuffer.wrap(bytes);
            }
            return ByteBuffer.wrap(dataRead);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
