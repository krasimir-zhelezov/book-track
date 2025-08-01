package dev.zhelezov.backend.auth.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.zhelezov.backend.auth.dto.SignInDto;
import dev.zhelezov.backend.auth.dto.SignUpDto;
import dev.zhelezov.backend.auth.dto.UserDto;
import dev.zhelezov.backend.auth.model.User;
import dev.zhelezov.backend.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserDto signUp(SignUpDto signUpDto) {
        return userRepository.save(new User(signUpDto.getEmail(), passwordEncoder.encode(signUpDto.getPassword1()))).toDto();
    }

    public UserDto signIn(SignInDto signInDto) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                signInDto.getEmail(),
                signInDto.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        // UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        if (authentication.isAuthenticated()) {
            System.out.println("Signed in as: " + authentication.getName());

            return userRepository.findByEmail(authentication.getName()).get().toDto();
        }   

        return null;
    }
}
