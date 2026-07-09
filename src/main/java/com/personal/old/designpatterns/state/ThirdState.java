package com.personal.old.designpatterns.state;

public class ThirdState implements IState {
    @Override
    public void performOperation(ObjectInQuestion objectInQuestion) {
        System.out.println("Perform in third state");
    }
}
