package com.personal.jms;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class MessageReceiver {

	public static void main(String... args) {
		try {
			receiveMessage();
		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		}
	}
	
	public static void receiveMessage() throws IOException, TimeoutException {
		String queueName = "hello";
		
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();
	    

	    
	    channel.queueDeclare(queueName, false, false, false, null);
	    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
	    	
	    Consumer consumer = new DefaultConsumer(channel) {
	    	@Override
	    	public void handleDelivery(String consumerTag, Envelope envelope,
	    			BasicProperties properties, byte[] body) throws IOException {
	    		String message = new String(body, "UTF-8");
	            System.out.println(" [x] Received '" + message + "'");
	    	}
	    };
		  
		channel.basicConsume(queueName, true, consumer);
	    
	    channel.close();
	    connection.close();
	}
}
