package com.beep.beep.global.security.jwt.exception.error;



import com.beep.beep.global.exception.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum JwtErrorProperty implements ErrorProperty {
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "만료된 토큰"),
    ILLEGAL_TOKEN(HttpStatus.UNAUTHORIZED, "잘못된 토큰"),
    TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "토큰이 존재하지 않음"),
    TOKEN_TYPE_ERROR(HttpStatus.BAD_REQUEST,"잘못된 타입"),
    UNSUPPORTED_TOKEN(HttpStatus.UNAUTHORIZED,"지원되지 않는 토큰");

    private final HttpStatus status;
    private final String message;
}
