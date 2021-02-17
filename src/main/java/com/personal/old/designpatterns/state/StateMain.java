package com.personal.old.designpatterns.state;

public class StateMain {
    public static void main(String[] args) {
        ObjectInQuestion object = new ObjectInQuestion(new FirstState());
        object.performOperation();
        object.performOperation();
        object.performOperation();
    }
}
