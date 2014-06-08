package com.personal.designpatterns;

import java.util.Observable;          //Observable is here
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class EventSource extends Observable implements Runnable {

    public void run() {
    	InputStreamReader isr = null;
    	BufferedReader br = null;
        try {
            isr = new InputStreamReader(System.in);
            br = new BufferedReader(isr);
            while (true) {
                String response = br.readLine();
                setChanged();
                notifyObservers(response);
            } 
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
        	try {
				br.close();
				isr.close();
			} catch (IOException e) {
				e.printStackTrace(System.out);
			}
        }
    }
}