package dev.zhelezov.backend.auth.controller;

import java.util.function.Supplier;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import dev.zhelezov.backend.auth.dto.AuthDto;
import dev.zhelezov.backend.auth.dto.SignInDto;
import dev.zhelezov.backend.auth.dto.SignUpDto;
import dev.zhelezov.backend.auth.dto.UserDto;
import dev.zhelezov.backend.auth.service.AuthService;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;
    
    @SecurityRequirements()
    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody SignInDto signInDto) {
        return handleAuthRequest(() -> authService.signIn(signInDto));
    }

    @SecurityRequirements()
    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto signUpDto) {
        return handleAuthRequest(() -> authService.signUp(signUpDto));
    }


    @GetMapping("/profile")
    public ResponseEntity<UserDto> profile() {
        return ResponseEntity.ok().body(authService.profile());
    }

    private ResponseEntity<?> handleAuthRequest(Supplier<AuthDto> authAction) {
    try {
        return ResponseEntity.ok().body(authAction.get());
    } catch (ResponseStatusException e) {
        return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
    }
}
}


