package com.personal.thrift;

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;

@SuppressWarnings("unchecked")
public class MultiplicationHandler implements com.personal.thrift.MultiplicationService.Iface, com.personal.thrift.MultiplicationService.AsyncIface {
    @Override
    public String multiply(int n1, int n2) throws TException {
        System.out.println("Sync multiply() called");
        return Integer.toString(n1 * n2);
    }

    @Override
    public void multiply(int n1, int n2, AsyncMethodCallback resultHandler) throws TException {
        System.out.println("Async multiply() called");
        if (n1 < 0 || n2 < 0) {
            resultHandler.onError(new NumberFormatException("Numbers can't be negative"));
        }
        resultHandler.onComplete(Integer.toString(n1 * n2));
    }
}