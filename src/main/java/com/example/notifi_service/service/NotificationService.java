package com.example.notifi_service.service;

import com.example.notifi_service.dto.NotificationRequest;
import com.example.notifi_service.dto.NotificationResponse;
import com.example.notifi_service.model.Notification;
import com.example.notifi_service.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.List;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository repository;

    public NotificationResponse save(NotificationRequest request) {
        Notification notification = Notification.builder()
                .userId(request.getUserId())
                .message(request.getMessage())
                .createdAt(LocalDateTime.now())
                .build();

        return toResponse(repository.save(notification));
    }

    public List<NotificationResponse> getByUser(UUID userId) {
        return repository.findByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private NotificationResponse toResponse(Notification n) {
        return NotificationResponse.builder()
                .id(n.getId())
                .userId(n.getUserId())
                .message(n.getMessage())
                .createdAt(n.getCreatedAt())
                .build();
    }
}