package com.chellefulk.api.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BCryptHashGenerator {
	@Bean
	public CommandLineRunner printBCryptHash() {
		return args -> {
			// String password = "Q7!vB2@kLp9#zX4w";
			// BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			// String hash = encoder.encode(password);
			// System.out.println("BCrypt hash for '" + password + "':");
			// System.out.println(hash);
		};
	}
}
