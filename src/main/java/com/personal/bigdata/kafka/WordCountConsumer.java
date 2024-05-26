package com.personal.bigdata.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class WordCountConsumer {


    public static final String WORD_COUNT_CONSUMER_TOPIC = "word-count-consumer";

    public static void main(final String[] argv) throws Exception {

        final CountDownLatch latch = new CountDownLatch(1);
        final ConsumerThread consumerRunnable = new ConsumerThread();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Stopping...");
            consumerRunnable.getKafkaConsumer().wakeup();
            latch.countDown();
        }));

        try {
            consumerRunnable.start();
            latch.await();
        } catch (final Exception e) {
            e.printStackTrace();
        }

        System.out.println("Stopped!");

    }

    private static class ConsumerThread extends Thread {

        private KafkaConsumer<String, Long> kafkaConsumer;

        public ConsumerThread() {
        }

        public void run() {

            final Properties configProperties = getProperties("1");
            this.kafkaConsumer = new KafkaConsumer<>(configProperties);
            kafkaConsumer.subscribe(Collections.singletonList(WORD_COUNT_CONSUMER_TOPIC));

            try {
                while (true) {
                    final ConsumerRecords<String, Long> records = kafkaConsumer.poll(100);
                    for (final ConsumerRecord<String, Long> record : records) {
                        final String key = record.key();
                        final Long value = record.value();
                        System.out.println(key + " " + value);
                    }
                }
            } catch (final Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("After closing KafkaConsumer");
                kafkaConsumer.close();
            }
        }

        public KafkaConsumer<String, Long> getKafkaConsumer() {
            return this.kafkaConsumer;
        }
    }

    private static Properties getProperties(final String groupId) {
        final Properties configProperties = new Properties();
        configProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        configProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.LongDeserializer");
        configProperties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        configProperties.put(ConsumerConfig.CLIENT_ID_CONFIG, "simple");
        return configProperties;
    }
}