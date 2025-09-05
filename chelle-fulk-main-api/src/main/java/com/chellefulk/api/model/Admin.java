package com.chellefulk.api.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "admin")
@Data
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "last_password_change", nullable = false)
    private LocalDateTime lastPasswordChange;

    @Column(nullable = false)
    private boolean locked;

    @Column(name="role", nullable = false)
    private String role;
}
