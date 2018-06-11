/**
 * 
 */
package com.personal.old.rabbitmq.ptp.helloworld;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author piyush
 *
 */
public class RabbitMQSenderMain {

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
	    String WARN = "WARN";
	    String ERROR = "ERROR";
	    String INFO = "INFO";
	    
	    MessageSender messageSender = new MessageSender();
	    for(int i = 0; i < 5; i++) {
	    	messageSender.sendMessage(channel, ERROR, "ERROR " + (i + 1));
	    	messageSender.sendMessage(channel, WARN, "WARN " + (i + 1));
			messageSender.sendMessage(channel, INFO, "INFO " + (i + 1));
	    }
	    
	    channel.close();
	    connection.close();
	}

}
