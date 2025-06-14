package com.personal.nio;

import com.personal.common.CustomTimer;

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
 * Below is blocking operation. FileChannels cannot work with non-blocking mode.
 */
public class ReadFile {

    public static void runUsingThreads(int size, Runnable runnable) {
        Thread[] threads = new Thread[size];
        for (int i = 0; i < size; i++) {
            threads[i] = new Thread(runnable);
        }
        for (Thread t : threads) {
            t.start();
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Please provide file absolute location");
            return;
        }
        String file = args[0];
        runUsingThreads(1, () -> {
            try {
                synchronousFileChannel(file);
            } catch (Exception e) {
            }
        });

        runUsingThreads(1, () -> {
            try {
                asyncFileChannel(file);
            } catch (Exception e) {
            }
        });
    }

    private static void asyncFileChannel(String file) throws IOException, InterruptedException, ExecutionException {
        CustomTimer timer = CustomTimer.create(true);
        Path path = Paths.get(file);
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
        long position = 0;
        while ((position = readUntilComplete(fileChannel, position)) != -1)
            ;
        System.out.println("Time taken for asyncFileChannel " + timer.elapsedFormatted());
    }

    private static long readUntilComplete(AsynchronousFileChannel fileChannel, long position)
            throws InterruptedException, ExecutionException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Future<Integer> operation = fileChannel.read(buffer, position);

        while (!operation.isDone()) {
            // looping until is done. similar to future.get()
        }
        if (operation.get() == -1) {
            return -1;
        }
        buffer.flip();
        byte[] data = new byte[buffer.limit()];
        ByteBuffer byteBuffer = buffer.get(data);
        System.out.println("Reading buffer of size: " + byteBuffer.remaining());
        System.out.print(new String(data));
        position = position + buffer.position();
        buffer.clear();
        return position;
    }

    private static void synchronousFileChannel(String file) throws IOException {
        CustomTimer timer = CustomTimer.create(true);
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        FileChannel fileChannel = randomAccessFile.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(48);

        int bytesRead = fileChannel.read(byteBuffer);
        while (bytesRead != -1) {
            byteBuffer.flip(); // make buffer ready for read
            while (byteBuffer.hasRemaining()) {
                byteBuffer.get();
                // System.out.print((char) byteBuffer.get()); // read 1 byte at a time
            }
            byteBuffer.clear(); // make buffer ready for writing
            bytesRead = fileChannel.read(byteBuffer);
        }
        randomAccessFile.close(); // Closes file channel as well
        System.out.println("Time taken for SynchronousFileChannel() " + timer.elapsedFormatted());
    }
}
