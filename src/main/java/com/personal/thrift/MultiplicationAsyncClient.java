package com.personal.thrift;

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.*;

import java.io.IOException;

public class MultiplicationAsyncClient {
    public static void main(String[] args) {


        try {
            TNonblockingTransport transport = new TNonblockingSocket("127.0.0.1", 9090);
            TProtocol protocol = new TCompactProtocol.Factory().getProtocol(transport);

            MultiplicationService.AsyncClient client = new MultiplicationService.AsyncClient(
                    new TCompactProtocol.Factory(),
                    new TAsyncClientManager(),
                    transport);


            client.multiply(3, 5, new AsyncMethodCallback() {
                @Override
                public void onComplete(Object response) {
                    System.out.println(response);
                }

                @Override
                public void onError(Exception exception) {
                    System.out.println(exception);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TException x) {
            x.printStackTrace();
        }
    }

}