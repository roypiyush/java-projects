package com.personal.old.designpatterns.state;

public class ObjectInQuestion {
    private IState state;
    public ObjectInQuestion(IState initialState) {
        this.state = initialState;
    }

    void setState(IState state) {
        this.state = state;
    }

    public void performOperation() {
        state.performOperation(this);
    }

}
