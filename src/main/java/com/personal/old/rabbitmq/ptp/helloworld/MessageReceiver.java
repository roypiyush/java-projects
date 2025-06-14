package com.personal.old.rabbitmq.ptp.helloworld;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.QueueingConsumer;

public class MessageReceiver {

	public void receiveMessage(Connection connection, String queueName) throws IOException, TimeoutException {

		Channel channel = connection.createChannel();

		channel.queueDeclare(queueName, false, false, false, null);

		@SuppressWarnings("deprecation")
		Consumer consumer = new QueueingConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope,
					BasicProperties properties, byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println(queueName + " " + message + "'");
			}
		};

		channel.basicConsume(queueName, true, consumer);

	}
}
