package com.personal.nio.client;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;


public class NIOClient {

    public static void main(String[] args) throws Exception {
        int port = Integer.parseInt(args[0]);
        InetSocketAddress remoteAddress = new InetSocketAddress("localhost", port);
        SocketChannel clientChannel = SocketChannel.open();
        clientChannel.configureBlocking(false);
        System.out.printf("%s %s %s %s %s\n", clientChannel.socket().isConnected(), clientChannel.socket().isBound(), clientChannel.socket().isClosed(), clientChannel.isOpen(), clientChannel.isConnected());
        boolean connect = clientChannel.connect(remoteAddress);
        while (!connect) {
            System.out.println("Called finish");
            connect = clientChannel.finishConnect();
        }
        System.out.printf("%s %s %s %s %s\n", clientChannel.socket().isConnected(), clientChannel.socket().isBound(), clientChannel.socket().isClosed(), clientChannel.isOpen(), clientChannel.isConnected());
        clientChannel.close();
        System.out.printf("%s %s %s %s %s\n", clientChannel.socket().isConnected(), clientChannel.socket().isBound(), clientChannel.socket().isClosed(), clientChannel.isOpen(), clientChannel.isConnected());
    }
}
