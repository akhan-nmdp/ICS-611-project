package com.baeldung.spring.kafka.monitoring.simulation;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerSimulator {

    @KafkaListener(topics = "${monitor.topic.name}",
      containerFactory = "kafkaListenerContainerFactory",
      autoStartup = "${monitor.consumer.simulate}")
    public void listenGroup(String message) throws InterruptedException {
        Thread.sleep(10L);
    }
}
