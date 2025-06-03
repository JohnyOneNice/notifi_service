package com.example.notifi_service.controller;

import com.example.notifi_service.dto.NotificationRequest;
import com.example.notifi_service.dto.NotificationResponse;
import com.example.notifi_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<NotificationResponse> send(@RequestBody @Valid NotificationRequest request) {
        return ResponseEntity.ok(notificationService.save(request));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<NotificationResponse>> getAll(@PathVariable UUID userId) {
        return ResponseEntity.ok(notificationService.getByUser(userId));
    }
}