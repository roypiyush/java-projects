package com.personal.nio.server;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.*;

public class NIOServer {
    static volatile boolean isStop = false;

    public static void main(String[] args) throws Exception {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> isStop = true));

        final ExecutorService schedulerExecutorService = Executors.newSingleThreadExecutor(new ThreadFactoryBuilder()
                .setNameFormat("Scheduler-%d")
                .setDaemon(true)
                .build());
        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 2, 15, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new ThreadFactoryBuilder()
                .setNameFormat("Worker-%d")
                .setDaemon(true)
                .build());
        final Selector selector = Selector.open();

        final Scheduler scheduler = new Scheduler(threadPoolExecutor, isStop);
        schedulerExecutorService.submit(scheduler);

        final ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        final ServerSocket socket = serverSocketChannel.socket();
        socket.bind(new InetSocketAddress("localhost", Integer.parseInt(args[0])));
        serverSocketChannel.configureBlocking(false);
        final SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println(selectionKey);

        while (!isStop) {
            int readyChannels = selector.select(); // block till channel is ready. Use select() for blocking op
            if (readyChannels == 0) {
                continue;
            }

            final Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
            while (selectionKeyIterator.hasNext()) {
                SelectionKey next = selectionKeyIterator.next();
                if (next.isAcceptable()) {
                    //System.out.println("New Connection " + socket.getLocalSocketAddress());
                    scheduler.scheduleSocket(socket.accept()); // Accepted new Socket connection
                } else if (next.isConnectable()) {
                    System.out.println("Connectable");
                } else if (next.isWritable()) {
                    System.out.println("Writable");
                } else {
                    System.out.println("Unknown state");
                }
                selectionKeyIterator.remove();
            }
        }
        scheduler.stop(isStop);
        schedulerExecutorService.shutdown();
        threadPoolExecutor.shutdown();
        System.out.println("Shutdown complete...");
    }
}
