package com.beep.beep.domain.auth.presentation.dto.request;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class TokenRefreshRequest {
    private String token;
}
