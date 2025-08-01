package dev.zhelezov.backend.auth;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findAll();
    Optional<User> findByEmail(String email);
}
