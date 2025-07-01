package com.example.notifi_service.service;

import com.example.notifi_service.dto.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class NotificationKafkaConsumer {
    private final NotificationService notificationService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger log = LoggerFactory.getLogger(NotificationKafkaConsumer.class);

    @KafkaListener(topics = "notification-send", groupId = "notifi-service")
    public void listen(String message) {
        try {
            NotificationRequest request = objectMapper.readValue(message, NotificationRequest.class);
            notificationService.saveFromKafka(request);
            log.info("Notification saved from Kafka: {}", request);
        } catch (Exception e) {
            log.error("Failed to process Kafka message: {}", message, e);
        }
    }
} 