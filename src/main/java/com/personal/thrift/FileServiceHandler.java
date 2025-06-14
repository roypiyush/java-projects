package com.personal.thrift;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

// Generated
public class FileServiceHandler implements FileService.Iface {
    private final Map<String, byte[]> fileStorageService = new HashMap<>();

    @Override
    public String sendFile(ByteBuffer data, String name) {
        byte[] bytes = new byte[data.remaining()];
        data.get(bytes);
        fileStorageService.put(name, bytes);
        return "File received: " + name;
    }

    @Override
    public ByteBuffer downloadFile(String filePath, int offset, int length) {
        byte[] data = fileStorageService.getOrDefault(filePath, new byte[0]);
        int to = Math.min(data.length, offset + length);
        byte[] slice = new byte[to - offset];
        System.arraycopy(data, offset, slice, 0, slice.length);
        return ByteBuffer.wrap(slice);
    }
}