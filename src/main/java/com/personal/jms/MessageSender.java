package com.personal.jms;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;

public class MessageSender {

	public void sendMessage(Channel channel, String queueName) throws IOException, TimeoutException {
		
	    channel.queueDeclare(queueName, false, false, false, null);
	    String message = "Hello World!";
	    channel.basicPublish("", queueName, null, message.getBytes());
	    System.out.println(" [x] Sent '" + message + "'");
	    
	}
}
