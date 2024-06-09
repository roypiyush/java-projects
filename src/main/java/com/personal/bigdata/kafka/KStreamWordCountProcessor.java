package com.personal.bigdata.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class KStreamWordCountProcessor {
    public static void main(String[] args) {

        final Properties props = getProperties(args[0]);

        final StreamsBuilder builder = createWordCountStream();
        try (KafkaStreams streams = new KafkaStreams(builder.build(), props)) {
            final CountDownLatch latch = new CountDownLatch(1);

            // attach shutdown handler to catch control-c
            Runtime.getRuntime().addShutdownHook(new Thread("streams-wordcount-shutdown-hook") {
                @Override
                public void run() {
                    streams.close();
                    latch.countDown();
                }
            });

            try {
                streams.start();
                latch.await();
            } catch (final Throwable e) {
                System.exit(1);
            }
        }
        System.out.println("Stopped!!");
        System.exit(0);
    }

    private static Properties getProperties(String bootstrapServers) {
        final Properties configProperties = new Properties();
        configProperties.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-wordcount");
        configProperties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProperties.put(StreamsConfig.STATESTORE_CACHE_MAX_BYTES_CONFIG, 0);
        configProperties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.StringSerde.class);
        configProperties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.StringSerde.class);
        configProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return configProperties;
    }

    static StreamsBuilder createWordCountStream() {
        final StreamsBuilder builder = new StreamsBuilder();
        final KStream<String, String> source = builder
                .stream(WordCountProducer.WORD_COUNT_PRODUCER_TOPIC);

        final KTable<String, Long> counts = source
                .flatMapValues(value -> {
                    return Arrays.asList(value.split("\\s+"));
                })
                .groupBy((key, value) -> {
                    return value;
                })
                .count();

        // need to override value serde to Long type
        counts.toStream().to(WordCountConsumer.WORD_COUNT_CONSUMER_TOPIC, Produced.with(Serdes.String(), Serdes.Long()));
        return builder;
    }
}
