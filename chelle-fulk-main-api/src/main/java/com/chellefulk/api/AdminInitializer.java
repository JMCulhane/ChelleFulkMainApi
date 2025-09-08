
package com.chellefulk.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class AdminInitializer implements CommandLineRunner {

    @Value("${admin.username}")
    private String adminUsername;
    @Value("${admin.password.hash}")
    private String adminPasswordHash;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) {
        // Only insert if the admin user does not already exist
        Integer count = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM admin WHERE username = ?",
            Integer.class,
            adminUsername
        );
        if (count == null || count == 0) {
            jdbcTemplate.update(
                "INSERT INTO admin (username, password_hash, last_password_change, locked, role) VALUES (?, ?, NOW(), false, 'ADMIN')",
                adminUsername, adminPasswordHash
            );
            System.out.println("Admin user '" + adminUsername + "' inserted.");
        } else {
            System.out.println("Admin user '" + adminUsername + "' already exists. No insert performed.");
        }
    }
}
