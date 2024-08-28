package com.beep.beep.domain.student.exception.error;



import com.beep.beep.global.exception.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StudentErrorProperty implements ErrorProperty {
    NOT_ALLOWED_ATTEND(HttpStatus.FORBIDDEN,"입실/퇴실을 하지 않았습니다.");

    private final HttpStatus status;
    private final String message;
}
