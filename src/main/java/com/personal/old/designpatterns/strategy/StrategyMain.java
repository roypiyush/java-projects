package com.personal.old.designpatterns.strategy;

import java.util.Random;

public class StrategyMain {
    public static void main(String[] args) {
        final Random random = new Random();
        final int a = random.nextInt();
        final int b = random.nextInt();
        final Context context = new Context();
        final StrategyFactory factory = new StrategyFactory();

        IStrategy strategy = factory.createStrategy(StrategyFactory.StrategyType.ADDITION);
        context.setStrategy(strategy);
        context.executeStrategy(a, b);

        strategy = factory.createStrategy(StrategyFactory.StrategyType.SUBTRACTION);
        context.setStrategy(strategy);
        context.executeStrategy(a, b);
    }
}
