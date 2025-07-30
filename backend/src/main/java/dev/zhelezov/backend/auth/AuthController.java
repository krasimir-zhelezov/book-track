package dev.zhelezov.backend.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    
    @PostMapping("/sign-in")
    public void signIn() {

    }

    @PostMapping("/sign-up")
    public void signUp() {
        
    }

    @GetMapping("/profile")
    public void profile() {

    }
}
