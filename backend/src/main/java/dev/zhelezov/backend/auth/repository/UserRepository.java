package dev.zhelezov.backend.auth.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.zhelezov.backend.auth.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findAll();
    Optional<User> findByEmail(String email);
}
