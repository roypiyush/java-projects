package com.personal.websocket.sample.test;

import com.personal.websocket.sample.server.WebSocketServer;


public class RunServer {
	
    public static void main(String[] args) throws Exception {
    	new WebSocketServer(8080, "/websocket").run();
    }

}
