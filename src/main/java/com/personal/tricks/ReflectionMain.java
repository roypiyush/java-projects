package com.personal.tricks;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface MyInterface {
	void service();
	Integer anotherService();
	String anotherService(String name);
}

public class ReflectionMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
        Class<?> interfaceClass = Class.forName("com.personal.tricks.MyInterface");

        /*
         * Get instance of a class which will act as proxy class to 
         * interface MyInterface
         */
        MyInterface implementingRunnable = (MyInterface) Proxy.newProxyInstance(
        		MyInterface.class.getClassLoader(),
                    new Class[] {interfaceClass},
                    new MyInvocationHandler() // Bind an handler to be called when interface's methods are called
        );

        implementingRunnable.service();
        System.out.println(implementingRunnable.anotherService());
        System.out.println(implementingRunnable.anotherService("My name"));

    }

    static class MyInvocationHandler implements InvocationHandler {

        public Object invoke(Object proxy, Method m, Object[] args)
                throws Throwable {
            System.out.println("called " + m);
            if(m.getReturnType().getName().equals("java.lang.Integer")) {
            	return new Integer(1);
            }
            else if(m.getReturnType().getName().equals("java.lang.String")) {
            	return args[0];
            }
            return new Object();
        }
    }

}
