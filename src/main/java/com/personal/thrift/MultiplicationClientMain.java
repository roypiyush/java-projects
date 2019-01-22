package com.personal.thrift;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class MultiplicationClientMain {
    public static void main(String[] args) {


        try {
            TTransport transport = new TSocket("localhost", 9090);
            transport.open();

            TProtocol protocol = new TBinaryProtocol(transport);
            MultiplicationService.Client client = new MultiplicationService.Client(protocol);

            int n1 = 3; int n2 = 5;
            String product = client.multiply(n1, n2);
            System.out.println(String.format("%s*%s=", n1, n2) + product);

            transport.close();
        } catch (TException x) {
            x.printStackTrace();
        }
    }

}