package ru.netology.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Listener {

    Logger logger = LoggerFactory.getLogger(Listener.class);

    @KafkaListener(topics = {"test.topic.json"})
    public void listen(String message) {
        logger.info("Received message: {}", message);
    }

}
