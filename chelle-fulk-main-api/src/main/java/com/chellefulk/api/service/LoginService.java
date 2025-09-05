package com.chellefulk.api.service;

import com.chellefulk.api.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.chellefulk.api.dto.AuthResponse;
import org.springframework.stereotype.Service;
import com.chellefulk.api.util.JwtUtil;

@Service
public class LoginService {
    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public LoginService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public AuthResponse authenticateAndRespond(String username, String password) {
        return adminRepository.findByUsernameAndLockedFalse(username)
            .filter(admin -> passwordEncoder.matches(password, admin.getPasswordHash()))
            .map(admin -> {
                String token = JwtUtil.generateToken(admin.getId(), admin.getUsername(), admin.getRole());
                return new AuthResponse(
                    admin.getId(),
                    admin.getUsername(),
                    admin.getRole(),
                    token
                );
            })
            .orElse(null);
    }
}
