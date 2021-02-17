package com.personal.old.designpatterns.state;

public class FirstState implements IState {
    @Override
    public void performOperation(ObjectInQuestion objectInQuestion) {
        System.out.println("Performs in first state");
        objectInQuestion.setState(new SecondState());
    }
}
