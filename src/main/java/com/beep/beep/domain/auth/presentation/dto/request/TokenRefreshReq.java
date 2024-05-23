package com.beep.beep.domain.auth.presentation.dto.request;

import lombok.Getter;

@Getter
public class TokenRefreshReq {
    private String token;
}
