package com.baeldung.kafka.retryable;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${message.topic.name}")
    private String topicName;

    @Value(value = "${partitioned.topic.name}")
    private String partitionedTopicName;

    @Value(value = "${filtered.topic.name}")
    private String filteredTopicName;

    @Value(value = "${greeting.topic.name}")
    private String greetingTopicName;

    @Value(value = "${multi.type.topic.name}")
    private String multiTypeTopicName;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topic1() {
        return new NewTopic(topicName, 1, (short) 1);
    }

    @Bean
    public NewTopic topic2() {
        return new NewTopic(partitionedTopicName, 6, (short) 1);
    }

    @Bean
    public NewTopic topic3() {
        return new NewTopic(filteredTopicName, 1, (short) 1);
    }

    @Bean
    public NewTopic topic4() {
        return new NewTopic(greetingTopicName, 1, (short) 1);
    }

    @Bean
    public NewTopic multiTypeTopic() {
        return new NewTopic(multiTypeTopicName, 1, (short) 1);
    }
}
