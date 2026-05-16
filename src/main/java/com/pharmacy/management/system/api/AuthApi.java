package com.pharmacy.management.system.api;

import com.pharmacy.management.system.config.JwtUtil;
import com.pharmacy.management.system.domain.User;
import com.pharmacy.management.system.domain.enums.UserRole;
import com.pharmacy.management.system.service.implementation.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthApi {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthApi(UserService userService,
                          PasswordEncoder passwordEncoder,
                          JwtUtil jwtUtil) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
            @RequestBody Map<String, String> credentials) {

        String username = credentials.get("username");
        String password = credentials.get("password");

        User user = userService.findUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.status(401).body(Map.of(
                "error", "Invalid credentials"));
        }

        String token = jwtUtil.generateToken(user);
        return ResponseEntity.ok(Map.of(
            "token", token,
            "username", user.getUsername(),
            "role", user.getRole().name()
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(
            @Valid @RequestBody User user) {

        if (user.getRole() == UserRole.ADMIN) {
            return ResponseEntity.status(403).body(Map.of(
                "error", "Public registration not allowed for ADMIN role"));
        }

        User saved = userService.saveUser(user);
        String token = jwtUtil.generateToken(saved);

        return ResponseEntity.status(201).body(Map.of(
            "message", "User registered successfully",
            "token", token,
            "data", saved
        ));
    }
}
