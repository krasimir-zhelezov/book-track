package dev.zhelezov.backend.auth;

import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signUp(SignUpDto signUpDto) {
        return userRepository.save(new User(signUpDto.getEmail(), signUpDto.getPassword1()));
    }
}
