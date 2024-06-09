package com.personal.bigdata.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;


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
        final CountDownLatch latch = new CountDownLatch(1);
        final Scanner in = new Scanner(System.in);
        final Properties configProperties = getProperties(args[0]);
        final KafkaProducer<String, String> producer = new KafkaProducer<>(configProperties);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            producer.close();
            latch.countDown();
        }));
        final Thread thread = new Thread(() -> {
            while (true) {
                String line = in.nextLine();
                final ProducerRecord<String, String> rec = new ProducerRecord<>(WORD_COUNT_PRODUCER_TOPIC, line);
                producer.send(rec);
            }
        });

        thread.start();
        latch.await();
        in.close();
    }

    private static Properties getProperties(String bootstrapServers) {
        final Properties configProperties = new Properties();
        configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        return configProperties;
    }
}