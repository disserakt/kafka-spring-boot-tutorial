package ru.netology.kafka;

import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public ProducerFactory<String, PayloadTimestampMessage> producerFactory(KafkaProperties properties) {
        Map<String, Object> configProps = properties.buildProducerProperties();
        return new DefaultKafkaProducerFactory<>(configProps, new StringSerializer(), new JsonSerializer<>());
    }

    @Bean
    public KafkaTemplate<String, PayloadTimestampMessage> kafkaTemplate(ProducerFactory<String, PayloadTimestampMessage> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

}
