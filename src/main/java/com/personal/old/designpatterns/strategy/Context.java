package com.personal.old.designpatterns.strategy;

public class Context {
    private volatile boolean isRunning = false;
    public IStrategy strategy;

    public boolean setStrategy(final IStrategy strategy) {
        if (isRunning) {
            // Modification should cause unwanted behavior. Running code should complete before setting
            return false;
        }
        this.strategy = strategy;
        return true;
    }

    public void executeStrategy(int a, int b) {
        isRunning = true;
        if (strategy == null) {
            System.out.println("Strategy not specified. Cannot execute!");
            return;
        }
        strategy.executeStrategy(a, b);
        isRunning = false;
    }
}
