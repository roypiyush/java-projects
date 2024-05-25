package com.personal.bigdata.kafka;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.errors.TopicExistsException;

import java.util.Collections;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class TopicOperations {
    public static void main(String[] args) {
        final String ordersTopic = "kafkatopic";
        Properties props = new Properties();

        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        try (final AdminClient adminClient = AdminClient.create(props)) {
            try {
                // Define topic
                NewTopic newTopic = new NewTopic(ordersTopic, 1, (short) 1);

                // Create topic, which is async call.
                final CreateTopicsResult createTopicsResult = adminClient.createTopics(Collections.singleton(newTopic));

                // Since the call is Async, Lets wait for it to complete.
                createTopicsResult.values().get(ordersTopic).get();
                final KafkaFuture<Set<String>> names = adminClient.listTopics().names();
                System.out.println(names.get());
            } catch (InterruptedException | ExecutionException e) {
                if (!(e.getCause() instanceof TopicExistsException))
                    throw new RuntimeException(e.getMessage(), e);
            }
        }
    }
}
