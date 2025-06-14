package com.personal.bigdata.kafka;

import com.personal.avro.User;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Base64;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class MessageConsumer {

    public static void main(final String[] argv) throws Exception {

        final ConsumerThread consumerRunnable = new ConsumerThread();
        final CountDownLatch latch = new CountDownLatch(1);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Stopping...");
            consumerRunnable.getKafkaConsumer().wakeup();
            latch.countDown();
        }));

        consumerRunnable.start();

        try {
            latch.await();
        } catch (final Exception e) {
            e.printStackTrace();
        }
        System.out.println("Stopped!");

    }

    private static class ConsumerThread extends Thread {

        private KafkaConsumer<String, String> kafkaConsumer;

        public ConsumerThread() {
        }

        public void run() {

            final Properties configProperties = getProperties("1");
            this.kafkaConsumer = new KafkaConsumer<>(configProperties);
            kafkaConsumer.subscribe(Collections.singletonList(MessageProducer.TOPIC_NAME));

            try {
                while (true) {
                    final DatumReader<User> datumReader = new SpecificDatumReader<>(User.class);
                    final ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
                    for (final ConsumerRecord<String, String> record : records) {
                        final String value = record.value();
                        System.out.println("----------- OUTPUT ------------ " + value);
                        final Decoder decoder = DecoderFactory.get().binaryDecoder(Base64.getDecoder().decode(value),
                                null);
                        final User user = datumReader.read(null, decoder);
                        System.out.println("Received User object : " + user.toString());

                    }
                }
            } catch (final Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("After closing KafkaConsumer");
                kafkaConsumer.close();
            }
        }

        public KafkaConsumer<String, String> getKafkaConsumer() {
            return this.kafkaConsumer;
        }
    }

    private static Properties getProperties(final String groupId) {
        final Properties configProperties = new Properties();
        configProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        configProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        configProperties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        configProperties.put(ConsumerConfig.CLIENT_ID_CONFIG, "simple");
        return configProperties;
    }
}