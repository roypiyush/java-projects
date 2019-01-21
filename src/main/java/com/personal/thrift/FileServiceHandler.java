package com.personal.thrift;

import org.apache.commons.io.IOUtils;
import org.apache.thrift.TException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class FileServiceHandler implements FileService.Iface {
    @Override
    public int sendFile(ByteBuffer fileData, String name) throws TException {
        byte[] data = fileData.array();
        System.out.println(String.format("Received File %s of size %s", name, data.length));
        try {
            IOUtils.write(data, new FileOutputStream("/tmp/" + name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
