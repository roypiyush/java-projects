package com.personal.thrift;

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;

@SuppressWarnings("unchecked")
public class MultiplicationHandler implements MultiplicationService.Iface, MultiplicationService.AsyncIface {

    @Override
    public int multiply(int n1, int n2) throws TException {
        System.out.println("Multiply(" + n1 + "," + n2 + ")");
        return n1 * n2;
    }

    @Override
    public void multiply(int n1, int n2, AsyncMethodCallback resultHandler) throws TException {
        if (n1 < 0 || n2 < 0) {
            resultHandler.onError(new NumberFormatException("Numbers can't be negative"));
        }
        resultHandler.onComplete(n1 * n2);
    }
}