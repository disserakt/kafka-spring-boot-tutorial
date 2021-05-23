package ru.netology.kafka;

public class PayloadTimestampMessage {

    public PayloadTimestampMessage() {
    }

    public PayloadTimestampMessage(String payload, long timestamp) {
        this.payload = payload;
        this.timestamp = timestamp;
    }

    public String payload;
    public long timestamp;

    @Override
    public String toString() {
        return "TestEvent{" +
                "payload='" + payload + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

}
