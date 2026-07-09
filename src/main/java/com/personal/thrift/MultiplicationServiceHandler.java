package com.personal.thrift;

// Generated
public class MultiplicationServiceHandler implements MultiplicationService.Iface {
    @Override
    public String multiply(int num1, int num2) {
        return String.valueOf(num1 * num2);
    }
}