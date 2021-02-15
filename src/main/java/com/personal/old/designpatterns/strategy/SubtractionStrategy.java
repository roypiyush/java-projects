package com.personal.old.designpatterns.strategy;

public class SubtractionStrategy implements IStrategy {
    final private int a;
    final private int b;

    public SubtractionStrategy(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void executeStrategy() {
        System.out.printf("Subtraction of a=%d and b=%d is %d\n", a, b, (a - b));
    }
}
