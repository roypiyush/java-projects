package com.personal.bigdata.kafka;

import com.personal.avro.User;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Base64;
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

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter message(type exit to quit)");

        // Configure the Producer
        Properties configProperties = new Properties();
        configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.ByteArraySerializer");
        configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(configProperties);
        String line = in.nextLine();
        while (!line.equals("exit") || !line.equals("quit")) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(out, null);
            DatumWriter<User> writer = new SpecificDatumWriter<>(User.getClassSchema());
            User user = User.newBuilder()
                    .setName(line)
                    .setFavoriteColor("blue")
                    .setFavoriteNumber(12)
                    .build();
            writer.write(user, encoder);
            encoder.flush();
            out.close();
            byte[] serializedBytes = out.toByteArray();
            ProducerRecord<String, String> rec = new ProducerRecord<>(TOPIC_NAME, Base64.getEncoder().encodeToString(serializedBytes));
            producer.send(rec);
            line = in.nextLine();
        }
        in.close();
        producer.close();
    }
}