package com.personal.thrift;

import com.google.common.collect.Sets;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;

import java.io.IOException;
import java.util.Set;

public class MultiplicationAsyncClientMain {
    public static void main(String[] args) {
        try {
            Set<Boolean> isComplete = Sets.newHashSet();
            TNonblockingTransport transport = new TNonblockingSocket("127.0.0.1", 9090);
            TAsyncClientManager clientManager = new TAsyncClientManager();
            MultiplicationService.AsyncClient client = new MultiplicationService.AsyncClient(
                    new TCompactProtocol.Factory(),
                    clientManager,
                    transport);


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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TException x) {
            x.printStackTrace();
        }
    }

}