package dev.zhelezov.backend.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.zhelezov.backend.auth.dto.UserDto;
import dev.zhelezov.backend.auth.model.User;
import dev.zhelezov.backend.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {
    
    private final UserRepository userRepository;

    public List<UserDto> getUsers() {
        return userRepository.findAll().stream().map(User::toDto).toList();
    }
}
