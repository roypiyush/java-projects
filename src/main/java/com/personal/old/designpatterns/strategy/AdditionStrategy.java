package com.personal.old.designpatterns.strategy;

public class AdditionStrategy implements IStrategy {

    final private int a;
    final private int b;

    public AdditionStrategy(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void executeStrategy() {
        System.out.printf("Addition of a=%d and b=%d is %d\n", a, b, (a + b));
    }
}
