package com.personal.old.designpatterns.state;

public interface IState {
    /**
     * Performs an operation and changes state
     */
    void performOperation(ObjectInQuestion objectInQuestion);
}
