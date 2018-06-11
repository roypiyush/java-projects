package com.personal.old.rabbitmq.ptp.helloworld;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;

public class MessageSender {

	public void sendMessage(Channel channel, String queueName, String message) throws IOException, TimeoutException {
		
	    channel.queueDeclare(queueName, false, false, false, null);
	    channel.basicPublish("", queueName, null, message.getBytes());
	    System.out.println(" [x] Sent '" + message + "'");
	    
	}
}
