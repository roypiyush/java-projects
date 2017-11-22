package com.personal.bigdata.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Scanner;


/*
 * /opt/kafka_2.11-0.10.2.0/bin/kafka-server-start.sh /opt/kafka_2.11-0.10.2.0/config/server.properties
 *
 * Create a topic
 * bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic <topic-name>
 *
 * Listing topics
 * bin/kafka-topics.sh --list --zookeeper localhost:2181
 *
 */
public class MessageProducer {

    public static final String TOPIC_NAME = "kafkatopic";

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Enter message(type exit to quit)");

        // Configure the Producer
        Properties configProperties = new Properties();
        configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.ByteArraySerializer");
        configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(configProperties);
        String line = in.nextLine();
        while (!line.equals("exit") || !line.equals("quit")) {
            ProducerRecord<String, String> rec = new ProducerRecord<String, String>(TOPIC_NAME, line);
            producer.send(rec);
            line = in.nextLine();
        }
        in.close();
        producer.close();
    }
}