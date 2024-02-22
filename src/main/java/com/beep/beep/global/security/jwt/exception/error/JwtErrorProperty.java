package com.beep.beep.global.security.jwt.exception.error;



import com.beep.beep.global.exception.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum JwtErrorProperty implements ErrorProperty {
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "만료된 토큰입니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),
    TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "토큰이 존재하지 않습니다."),
    REFRESH_NOT_FOUND(HttpStatus.NOT_FOUND,"리프레시 토큰이 존재하지 않습니다."),
    REFRESH_EXPIRED(HttpStatus.UNAUTHORIZED,"리프레시 토큰이 만료되었습니다."),
    UNEXPIRED_TOKEN(HttpStatus.UNAUTHORIZED,"만료되지 않은 토큰입니다.");

    private final HttpStatus status;
    private final String message;
}
