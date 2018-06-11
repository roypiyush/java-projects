package com.personal.old.tricks;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

abstract class MyAbstract {
    static void utilMethod() {

    }
}

interface Base {
    default void base() {
        System.out.println("Base Interface Method");
    }
}

interface MyInterface1 extends Base {
    static void m() {

    }

    default void someMethod() {
        System.out.println("From MyInterface1");
    }
}

interface MyInterface2 extends MyInterface1 {
    default void someMethod() {
        System.out.println("From MyInterface2");
    }
}

interface MyInterface3 extends Base {
    default void someMethod() {
        System.out.println("From MyInterface3");
    }
}

class MyClass implements MyInterface1, MyInterface2, MyInterface3 {

    public void someMethod() {
        MyInterface3.super.someMethod();
    }
    void m() {
        someMethod();
    }
}

public class LambdaMain {

    public static Optional<String> anOptionalReturn(int i, Predicate<Integer> predicate) {
        if (predicate.test(i)) {
            return Optional.of("Success");
        }
        else {
            return Optional.empty();
        }
    }


    public static void main(String[] args) {
        Consumer<String> c = s -> System.out.println("asfasfd" + s);
        c.accept("vbnvbnvbn");

        Optional<String> result1 = anOptionalReturn(10, i -> i > 0);
        Optional<String> result2 = anOptionalReturn(-10, i -> i > 0);

        System.out.printf("%s valueIsPresent: %s value: %s\n", result1, result1.isPresent(), result1.get());
        System.out.printf("%s valueIsPresent: %s\n", result2, result2.isPresent());
        try {
            result2.get();
        } catch (NoSuchElementException e) {
            System.out.println("There is no element");
        }

        MyClass myClass = new MyClass();
        myClass.m();
        myClass.base();
    }
}
