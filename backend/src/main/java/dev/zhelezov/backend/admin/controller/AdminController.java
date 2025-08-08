package dev.zhelezov.backend.admin.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.zhelezov.backend.admin.service.AdminService;
import dev.zhelezov.backend.auth.dto.UserDto;
import dev.zhelezov.backend.auth.service.AuthService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok().body(adminService.getUsers());
    }
}
