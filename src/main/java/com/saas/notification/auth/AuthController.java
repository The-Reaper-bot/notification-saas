package com.saas.notification.auth;

import com.saas.notification.user.UserService;
import com.saas.notification.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/signup")
    public Map<String, String> signup(@RequestBody Map<String, String> req) {

        Users user = userService.signup(req);

        String token = jwtUtil.generateToken(
                user.getId(),
                user.getTenantId(),
                user.getRole()
        );

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> req) {

        logger.info("Logging in user with email: " + req.get("email"));

        Users user = userService.login(req);

        String token = jwtUtil.generateToken(
                user.getId(),
                user.getTenantId(),
                user.getRole()
        );

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }
}
