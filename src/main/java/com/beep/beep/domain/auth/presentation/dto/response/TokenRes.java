package com.beep.beep.domain.auth.presentation.dto.response;

import lombok.Builder;

@Builder
public record TokenRes(String accessToken, String refreshToken) {
}
