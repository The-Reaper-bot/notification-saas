package com.saas.notification.usage;

import com.saas.notification.common.TenantContext;
import com.saas.notification.tenant.Tenant;
import com.saas.notification.tenant.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsageService {

    private final UsageRepository usageRepository;
    private final TenantRepository tenantRepository;

    public void checkAndIncrementSms() {

        UUID tenantId = TenantContext.getTenantId();
        String month = YearMonth.now().toString(); // yyyy-MM

        UsageStats usage = usageRepository
                .findByTenantIdAndMonth(tenantId, month)
                .orElseGet(() -> {
                    UsageStats u = new UsageStats();
                    u.setTenantId(tenantId);
                    u.setMonth(month);
                    u.setSmsCount(0);
                    return u;
                });

        Tenant tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new RuntimeException("Tenant not found"));

        int limit = getLimitForPlan(tenant.getPlan());

        if (usage.getSmsCount() >= limit) {
            throw new RuntimeException("Monthly SMS quota exceeded");
        }

        usage.setSmsCount(usage.getSmsCount() + 1);
        usageRepository.save(usage);
    }

    private int getLimitForPlan(String plan) {
        if ("PRO".equalsIgnoreCase(plan)) {
            return 5000;
        }
        return 100; // FREE plan
    }
}
