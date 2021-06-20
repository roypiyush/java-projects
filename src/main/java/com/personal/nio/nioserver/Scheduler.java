package com.personal.nio.nioserver;

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

    public Scheduler(final ExecutorService executorService, final Selector selector) {
        this.selector = selector;
        this.executorService = executorService;
    }

    public void scheduleSocket(final Socket socket) throws IOException {
        final SocketChannel channel = socket.getChannel();
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_READ);
        selector.wakeup();
    }

    @Override
    public void run() {
        try {
            while (true) {
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
