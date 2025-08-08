package dev.zhelezov.backend;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import dev.zhelezov.backend.auth.model.Role;
import dev.zhelezov.backend.auth.model.User;
import dev.zhelezov.backend.auth.repository.UserRepository;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
    public CommandLineRunner initAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByEmail("admin@example.com").isEmpty()) {
                User admin = new User("admin@example.com", passwordEncoder.encode("admin123"), Role.ADMIN);
                userRepository.save(admin);
                System.out.println("Created default admin account");
            }
        };
    }
}
