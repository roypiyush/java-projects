package com.personal.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Below is blocking operatoin. FileChannels cannot work with non-blocking mode.
 */
public class ReadFile {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Please provide file absolute location");
            return;
        }
        String file = args[0];
        synchronousFileChannel(file);
        System.out.println("########");
        asyncFileChannel(file);
        return;
    }

    private static void asyncFileChannel(String file) throws IOException, InterruptedException, ExecutionException {
        Path path = Paths.get(file);
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
        long position = 0;
        while((position = readUntilComplete(fileChannel, position)) != -1);
    }

    private static long readUntilComplete(AsynchronousFileChannel fileChannel, long position) throws InterruptedException, ExecutionException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Future<Integer> operation = fileChannel.read(buffer, position);

        while(!operation.isDone()) {
            // looping until is done. similar to future.get()
        }
        if (operation.get() == -1) {
            return -1;
        }
        buffer.flip();
        byte[] data = new byte[buffer.limit()];
        buffer.get(data);
        System.out.print(new String(data));
        position = position + buffer.position();
        buffer.clear();
        return position;
    }

    private static void synchronousFileChannel(String file) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        FileChannel fileChannel = randomAccessFile.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(48);

        int bytesRead = fileChannel.read(byteBuffer);
        while (bytesRead != -1) {
            byteBuffer.flip();  // make buffer ready for read
            while(byteBuffer.hasRemaining()){
                System.out.print((char) byteBuffer.get()); // read 1 byte at a time
            }
            byteBuffer.clear(); // make buffer ready for writing
            bytesRead = fileChannel.read(byteBuffer);
        }
        randomAccessFile.close(); // Closes file channel as well
    }
}
