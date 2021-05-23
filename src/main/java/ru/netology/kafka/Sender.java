package ru.netology.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.time.Instant;
import java.util.UUID;

@Service
public class Sender {

    Logger logger = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Scheduled(fixedDelay = 1000L)
    public void sendMessage() {
        String data = "{\"payload\":\"Hello, world!\",\"timestamp\":" + Instant.now().toEpochMilli() + "}";
        ListenableFuture<SendResult<String, String>> sendResult = kafkaTemplate.send(
                "test.topic.plain.text",
                UUID.randomUUID().toString(),
                data
        );
        sendResult.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable ex) {
                logger.info("Failed to send message: {}", data);
                logger.error("Error", ex);
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info("Successfully sent message: {}", result.getProducerRecord().value());
            }
        });
    }

}
