package dev.zhelezov.backend.auth.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.zhelezov.backend.auth.dto.AuthDto;
import dev.zhelezov.backend.auth.dto.CustomUserDetails;
import dev.zhelezov.backend.auth.dto.SignInDto;
import dev.zhelezov.backend.auth.dto.SignUpDto;
import dev.zhelezov.backend.auth.dto.UserDto;
import dev.zhelezov.backend.auth.model.Role;
import dev.zhelezov.backend.auth.model.User;
import dev.zhelezov.backend.auth.repository.UserRepository;
import dev.zhelezov.backend.config.JwtUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    public AuthDto signUp(SignUpDto signUpDto) {
        User user;

        if (!signUpDto.getPassword1().equals(signUpDto.getPassword2())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passwords do not match");
        }
        try {
            user = userRepository.save(new User(signUpDto.getEmail(), passwordEncoder.encode(signUpDto.getPassword1()), Role.MEMBER));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
        }

        final CustomUserDetails userDetails = userDetailsService.loadUserByUsername(signUpDto.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);

        return new AuthDto(user.toDto(), jwt);
    }

    public AuthDto signIn(SignInDto signInDto) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    signInDto.getEmail(), 
                    signInDto.getPassword()
                )
            );
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        final CustomUserDetails userDetails = userDetailsService.loadUserByUsername(signInDto.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);

        return new AuthDto(new UserDto(userDetails.getUsername(), userDetails.getRoles().get(0)), jwt);
    }

    public UserDto profile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof AnonymousAuthenticationToken) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }

        if (authentication.getPrincipal() instanceof User) {
            return ((User) authentication.getPrincipal()).toDto();
        }

        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            return new UserDto(userDetails.getUsername(), userDetails.getRoles().get(0));
        }

        return null;
    }

    public List<UserDto> getUsers() {
        return userRepository.findAll().stream().map(User::toDto).toList();
    }
}
