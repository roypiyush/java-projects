package com.personal.bigdata.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Base64;
import java.util.Properties;
import java.util.Scanner;


/*
 * $KAFKA_HOME/bin/kafka-server-start.sh $KAFKA_HOME/config/server.properties
 *
 * Create a topic
 * bin/kafka-topics.sh --create --replication-factor 1 --partitions 1 --topic <topic-name>
 *
 * Listing topics
 * bin/kafka-topics.sh --list --zookeeper localhost:2181
 *
 */
public class WordCountProducer {

    public static final String WORD_COUNT_PRODUCER_TOPIC = "word-count-producer";

    public static void main(final String[] args) throws Exception {
        final Scanner in = new Scanner(System.in);
        final Properties configProperties = getProperties();

        final KafkaProducer<String, String> producer = new KafkaProducer<>(configProperties);
        String line = in.nextLine();
        while (!line.equals("exit") || !line.equals("quit")) {
            final ProducerRecord<String, String> rec = new ProducerRecord<>(WORD_COUNT_PRODUCER_TOPIC, line);
            producer.send(rec);
            line = in.nextLine();
        }
        in.close();
        producer.close();
    }

    private static Properties getProperties() {
        final Properties configProperties = new Properties();
        configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        return configProperties;
    }
}