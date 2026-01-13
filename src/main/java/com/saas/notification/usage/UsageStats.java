package com.saas.notification.usage;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class UsageStats {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID tenantId;
    private String month;   // yyyy-MM
    private int smsCount;
}
