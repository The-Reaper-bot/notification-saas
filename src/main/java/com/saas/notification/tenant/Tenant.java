package com.saas.notification.tenant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Tenant {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String plan;    // FREE / PRO
    private String status;  // ACTIVE / SUSPENDED
}
