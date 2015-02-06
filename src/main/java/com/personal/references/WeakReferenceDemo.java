package com.personal.references;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/*
 * Run this class using -Xms1m and -Xmx1m to see weak reference 
 * garbage collected.
 * 
 */
public class WeakReferenceDemo {

	private WeakReference<Map<Integer, String>> myMap;

    public static void main(String[] args) {
        new WeakReferenceDemo().doFunction();
    }

    private void doFunction() {

        Map<Integer, String> map = new HashMap<Integer, String>();
        myMap = new WeakReference<Map<Integer, String>>(map);

        map = null; // making weakly referenced
        
        int i = 0;
        while (true) {
        	// Use get() method to refer to actual object
            if (myMap.get() != null) {
                myMap.get().put(i++, "test" + i);

                System.out.println("im still working!!!!");
            } else {
                System.out
                .println("*******im free*******");
                break;

            }

        }
    }


}
