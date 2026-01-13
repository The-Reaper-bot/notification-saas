package com.saas.notification.notification;

import com.saas.notification.common.TenantContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.saas.notification.usage.UsageService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository repository;
    private final SmsSender smsSender;
    private final UsageService usageService;


    public Notification create(String type, String recipient, String message) {

        UUID tenantId = TenantContext.getTenantId();

        Notification notification = new Notification();
        notification.setTenantId(tenantId);
        notification.setType(type);
        notification.setRecipient(recipient);
        notification.setMessage(message);

        boolean sent = false;

        if ("SMS".equalsIgnoreCase(type)) {
            usageService.checkAndIncrementSms();
            sent = smsSender.send(recipient, message);
        }


        notification.setStatus(sent ? "SENT" : "FAILED");

        return repository.save(notification);
    }

    public List<Notification> getAllForTenant() {
        UUID tenantId = TenantContext.getTenantId();
        return repository.findByTenantId(tenantId);
    }

}
