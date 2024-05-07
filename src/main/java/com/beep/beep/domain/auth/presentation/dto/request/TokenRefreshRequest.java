package com.beep.beep.domain.auth.presentation.dto.request;

import lombok.Getter;

@Getter
public class TokenRefreshRequest {
    private String token;
}
