package com.personal.old.designpatterns.strategy;

public class StrategyFactory {

    enum StrategyType {
        ADDITION {
            public IStrategy createStrategy() {
                return new AdditionStrategy();
            }
        },

        SUBTRACTION {
            public IStrategy createStrategy() {
                return new SubtractionStrategy();
            }
        },

        ;

        public IStrategy createStrategy() {
            return null;
        }
    }

    public IStrategy createStrategy(StrategyType type) {
        return type.createStrategy();
    }
}
