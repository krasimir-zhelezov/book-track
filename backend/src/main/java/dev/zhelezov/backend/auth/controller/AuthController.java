package dev.zhelezov.backend.auth.controller;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import dev.zhelezov.backend.auth.dto.SignInDto;
import dev.zhelezov.backend.auth.dto.SignUpDto;
import dev.zhelezov.backend.auth.dto.UserDto;
import dev.zhelezov.backend.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
@Tag(name = "Authentication Management", description = "Endpoints for managing authentication")
public class AuthController {

    private final AuthService authService;
    
    @SecurityRequirements()
    @PostMapping("/sign-in")
    @Operation(summary = "Sign in", description = "Authenticate an existing user and return user details with JWT token")
    @ApiResponse(responseCode = "200", description = "Authentication successful")
    @ApiResponse(responseCode = "401", description = "Invalid credentials", content = @Content())
    public ResponseEntity<?> signIn(@RequestBody SignInDto signInDto) {
        return handleAuthRequest(() -> authService.signIn(signInDto));
    }

    @SecurityRequirements()
    @PostMapping("/sign-up")
    @Operation(summary = "Sign up", description = "Register a new user, authenticate them, and return user details with JWT token")
    @ApiResponse(responseCode = "200", description = "Registration and authentication successful")
    @ApiResponse(responseCode = "400", description = "Bad request (password mismatch, user already exists)", content = @Content())
    public ResponseEntity<?> signUp(@RequestBody SignUpDto signUpDto) {
        return handleAuthRequest(() -> authService.signUp(signUpDto));
    }


    @GetMapping("/profile")
    @Operation(summary = "User details", description = "Returns the authenticated user's details")
    @ApiResponse(responseCode = "200", description = "User details found")
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content())
    public ResponseEntity<?> profile() {
        return handleAuthRequest(() -> authService.profile());
    }

    private ResponseEntity<?> handleAuthRequest(Supplier<?> authAction) {
        try {
            return ResponseEntity.ok().body(authAction.get());
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/users")
    @Operation(summary = "Get all users", description = "Returns a list of all users if the user has admin role")
    @ApiResponse(responseCode = "200", description = "Users found")
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content())
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok().body(authService.getUsers());
    }
}


