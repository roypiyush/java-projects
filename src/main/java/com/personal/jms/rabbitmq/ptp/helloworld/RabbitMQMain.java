/**
 * 
 */
package com.personal.jms.rabbitmq.ptp.helloworld;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author piyush
 *
 */
public class RabbitMQMain {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws TimeoutException 
	 */
	public static void main(String[] args) throws IOException, TimeoutException {
		
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();
	    String queueName = "hello";
	    
	    for(int i = 0; i < 5; i++) {
	    	new MessageReceiver().receiveMessage(connection, queueName);
		    new MessageSender().sendMessage(channel, queueName, "hello " + (i + 1));
	    }
	    
	    channel.close();
	    connection.close();
	}

}