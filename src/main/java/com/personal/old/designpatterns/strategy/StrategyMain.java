package com.personal.old.designpatterns.strategy;

import java.util.Random;

public class StrategyMain {
    public static void main(String[] args) {
        Random random = new Random();
        final int a = random.nextInt();
        final int b = random.nextInt();
        Context context = new Context();

        context.setStrategy(new AdditionStrategy(a, b));
        context.executeStrategy();

        context.setStrategy(new SubtractionStrategy(a, b));
        context.executeStrategy();
    }
}
