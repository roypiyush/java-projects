package com.personal.thrift;

import com.google.common.collect.Sets;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class MultiplicationAsyncClientMain {
    public static void main(String[] args) {
        try {
            Set<Boolean> isComplete = Sets.newHashSet();
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 9090));
            TNonblockingTransport transport = new TNonblockingSocket(socketChannel);
            TAsyncClientManager clientManager = new TAsyncClientManager();
            MultiplicationService.AsyncClient client = new MultiplicationService.AsyncClient(
                    new TBinaryProtocol.Factory(),
                    clientManager,
                    transport);
            transport.finishConnect();

            client.multiply(3, 5, new AsyncMethodCallback<String>() {
                @Override
                public void onComplete(String response) {
                    System.out.println(response);
                    isComplete.add(true);
                }

                @Override
                public void onError(Exception exception) {
                    exception.printStackTrace();
                    isComplete.add(true);
                }
            });
            while(clientManager.isRunning()) {
                if (isComplete.size() == 1) {
                    isComplete.clear();
                    clientManager.stop();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}