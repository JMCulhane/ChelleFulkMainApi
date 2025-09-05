package com.chellefulk.api.controller;


import com.chellefulk.api.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.chellefulk.api.dto.LoginRequest;
import com.chellefulk.api.dto.AuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        logger.info("LoginController hit: username={}", request.getUsername());
        try {
            AuthResponse response = loginService.authenticateAndRespond(request.getUsername(), request.getPassword());
            if (response == null) {
                logger.warn("Authentication failed for username={}", request.getUsername());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials or user locked.");
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Exception during login for username={}: {}", request.getUsername(), e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error.");
        }
    }
}
