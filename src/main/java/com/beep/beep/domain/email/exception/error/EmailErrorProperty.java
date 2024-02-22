package com.beep.beep.domain.email.exception.error;


import com.beep.beep.global.exception.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum EmailErrorProperty implements ErrorProperty {
    EMAIL_CONFLICT(HttpStatus.CONFLICT, "해당 이메일의 계정이 이미 존재합니다."),
    INVALID_CODE(HttpStatus.BAD_REQUEST,"인증번호가 유효하지 않습니다."),
    EMAIL_NOT_FOUND(HttpStatus.NOT_FOUND,"이메일이 존재하지 않습니다.");

    private final HttpStatus status;
    private final String message;
}
