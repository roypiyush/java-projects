package com.personal.nio.nioserver;

import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SocketChannel;

public class ConnectionHandler implements Runnable {
    private final SocketChannel channel;

    public ConnectionHandler(final SocketChannel channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        try {
            readFromChannel(channel);
        } catch (ClosedChannelException e) {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readFromChannel(final SocketChannel channel) throws Exception {
        final ByteBuffer buffer = ByteBuffer.allocate(1024);
        if (channel.isOpen() && channel.isConnected()) {
            final int bytesRead = channel.read(buffer);
            if (bytesRead == -1) {
                //System.out.println(socket.getRemoteSocketAddress() + " closed");
                channel.socket().close();
                return;
            }
            if (bytesRead == 0) {
                return;
            }
            final Thread thread = Thread.currentThread();
            System.out.println(thread + " " + bytesRead + " bytes read on " + channel.socket().getRemoteSocketAddress());

        }
    }
}