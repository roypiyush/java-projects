package com.personal.old.designpatterns.strategy;

public class Context {
    public IStrategy strategy;

    public void setStrategy(final IStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy(int a, int b) {
        if (strategy == null) {
            System.out.println("Strategy not specified. Cannot execute!");
            return;
        }
        strategy.executeStrategy(a, b);
    }
}
