package ru.netology.kafka;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, PayloadTimestampMessage> consumerFactory(KafkaProperties properties) {
        Map<String, Object> props = properties.buildConsumerProperties();
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(PayloadTimestampMessage.class, false));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PayloadTimestampMessage> messageListener(ConsumerFactory<String, PayloadTimestampMessage> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, PayloadTimestampMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }


}
