package com.saas.notification.auth;

import com.saas.notification.user.User;
import com.saas.notification.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public Map<String, String> signup(@RequestBody Map<String, String> req) {

        User user = userService.signup(req);

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

        User user = userService.login(req);

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
