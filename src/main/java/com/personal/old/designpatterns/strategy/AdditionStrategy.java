package com.personal.old.designpatterns.strategy;

public class AdditionStrategy implements IStrategy {

    @Override
    public void executeStrategy(int a, int b) {
        System.out.printf("Addition of a=%d and b=%d is %d\n", a, b, (a + b));
    }
}
