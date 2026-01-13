package com.saas.notification.notification;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public Notification createNotification(@RequestBody Map<String, String> req) {

        return notificationService.create(
                req.get("type"),
                req.get("recipient"),
                req.get("message")
        );
    }

    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationService.getAllForTenant();
    }

}
