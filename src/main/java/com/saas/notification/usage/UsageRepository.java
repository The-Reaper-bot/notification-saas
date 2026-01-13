package com.saas.notification.usage;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsageRepository extends JpaRepository<UsageStats, UUID> {

    Optional<UsageStats> findByTenantIdAndMonth(UUID tenantId, String month);
}
