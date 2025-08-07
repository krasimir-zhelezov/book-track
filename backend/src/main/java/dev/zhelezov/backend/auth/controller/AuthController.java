package dev.zhelezov.backend.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<UserDto> signIn(@RequestBody SignInDto signInDto) {
        try {
            UserDto dto = authService.signIn(signInDto);

            return ResponseEntity.ok().body(dto);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @SecurityRequirements()
    @PostMapping("/sign-up")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpDto signUpDto) {
        return ResponseEntity.ok().body(authService.signUp(signUpDto));
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDto> profile() {
        return ResponseEntity.ok().body(authService.profile());
    }
}


