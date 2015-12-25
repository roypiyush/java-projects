package com.personal.jms;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class MessageSender {

	public static void main(String... args) {
		try {
			sendMessage();
		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		}
	}
	
	public static void sendMessage() throws IOException, TimeoutException {
		String queueName = "hello";
		
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();
	    
	    channel.queueDeclare(queueName, false, false, false, null);
	    String message = "Hello World!";
	    channel.basicPublish("", queueName, null, message.getBytes());
	    System.out.println(" [x] Sent '" + message + "'");
	    
	    channel.close();
	    connection.close();
	}
}
