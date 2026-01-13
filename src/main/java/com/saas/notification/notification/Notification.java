package com.saas.notification.notification;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class Notification {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID tenantId;
    private String type;       // SMS / EMAIL / WHATSAPP
    private String recipient;  // phone or email
    private String message;
    private String status;     // PENDING / SENT / FAILED

    private LocalDateTime createdAt = LocalDateTime.now();
}
