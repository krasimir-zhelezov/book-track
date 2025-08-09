package dev.zhelezov.backend.admin.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.zhelezov.backend.admin.service.AdminService;
import dev.zhelezov.backend.auth.dto.UserDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/admin")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers(Authentication authentication) {
        System.out.println("Current user: " + authentication.getName());
        System.out.println("Authorities: " + authentication.getAuthorities());
        return ResponseEntity.ok().body(adminService.getUsers());
    }
}
