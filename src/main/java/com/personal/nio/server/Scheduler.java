package com.personal.nio.server;

import java.io.IOException;
import java.net.Socket;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;

public class Scheduler implements Runnable {
    final ExecutorService executorService;
    final Selector selector;
    volatile boolean isStop;

    public Scheduler(final ExecutorService executorService, boolean isStop) throws IOException {
        this.selector = Selector.open();
        this.executorService = executorService;
        this.isStop = isStop;
    }

    public void scheduleSocket(final Socket socket) throws IOException {
        final SocketChannel channel = socket.getChannel();
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_READ);
        selector.wakeup();
    }

    public void stop(final boolean isStop) {
        this.isStop = isStop;
    }

    @Override
    public void run() {
        try {
            while (!isStop) {
                int channelCount = selector.select();
                if (channelCount == 0) {
                    continue;
                }
                final Set<SelectionKey> selectionKeys = selector.selectedKeys();
                final Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    final SelectionKey key = iterator.next();
                    if (key.isValid()) {
                        final SelectableChannel channel = key.channel();
                        if (channel instanceof SocketChannel) {
                            final SocketChannel socketChannel = (SocketChannel) channel;
                            if (!socketChannel.socket().isClosed()) {
                                if (key.isReadable()) {
                                    executorService.submit(new ConnectionHandler(socketChannel));
                                } else if (key.isWritable()) {

                                } else if (key.isConnectable()) {

                                } else {
                                    System.out.println("Unexpected event " + key.interestOps());
                                }
                            }
                        }
                    } else {
                        key.cancel();
                    }
                    try { iterator.remove(); } catch (ConcurrentModificationException e) {}
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
