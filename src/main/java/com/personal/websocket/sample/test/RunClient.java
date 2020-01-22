package com.personal.websocket.sample.test;

import com.personal.websocket.sample.client.WebSocketClient;

public class RunClient {
	
	static final String URL = System.getProperty("url", "ws://127.0.0.1:8080/websocket");
	
	public static void main(String[] args) throws Exception {
    	new WebSocketClient().connect(URL);
    }
}
