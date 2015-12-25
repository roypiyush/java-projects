package com.personal.jms;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class MessageReceiver {

	public void receiveMessage(Connection connection, String queueName) throws IOException, TimeoutException {
		
	    Channel channel = connection.createChannel();
	    
	    channel.queueDeclare(queueName, false, false, false, null);
	    	
	    Consumer consumer = new DefaultConsumer(channel) {
	    	@Override
	    	public void handleDelivery(String consumerTag, Envelope envelope,
	    			BasicProperties properties, byte[] body) throws IOException {
	    		String message = new String(body, "UTF-8");
	            System.out.println(" [*] Received '" + message + "'");
	    	}
	    };
		  
		channel.basicConsume(queueName, true, consumer);
	    
	}
}
