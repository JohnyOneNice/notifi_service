package com.example.notifi_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class NotificationRequest {
    @NotNull
    private UUID userId;

    @NotBlank
    private String message;
}