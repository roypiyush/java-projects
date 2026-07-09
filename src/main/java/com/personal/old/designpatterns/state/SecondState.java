package com.personal.old.designpatterns.state;

public class SecondState implements IState {
    @Override
    public void performOperation(ObjectInQuestion objectInQuestion) {
        System.out.println("Perform in second state");
        objectInQuestion.setState(new ThirdState());
    }
}
