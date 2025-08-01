package dev.zhelezov.backend.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignUpDto {
    private String email;
    private String password1;
    private String password2;
}
