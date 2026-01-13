package com.saas.notification.user;

import com.saas.notification.tenant.Tenant;
import com.saas.notification.tenant.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TenantRepository tenantRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User signup(Map<String, String> req) {

        Tenant tenant = new Tenant();
        tenant.setName(req.get("company"));
        tenant.setPlan("FREE");
        tenant.setStatus("ACTIVE");
        tenantRepository.save(tenant);

        User user = new User();
        user.setTenantId(tenant.getId());
        user.setEmail(req.get("email"));
        user.setPassword(encoder.encode(req.get("password")));
        user.setRole("ADMIN");

        return userRepository.save(user);
    }

    public User login(Map<String, String> req) {
        User user = userRepository.findByEmail(req.get("email"))
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(req.get("password"), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return user;
    }
}
