package com.personal.old.designpatterns.strategy;

public class SubtractionStrategy implements IStrategy {

    @Override
    public void executeStrategy(int a, int b) {
        System.out.printf("Subtraction of a=%d and b=%d is %d\n", a, b, (a - b));
    }
}
