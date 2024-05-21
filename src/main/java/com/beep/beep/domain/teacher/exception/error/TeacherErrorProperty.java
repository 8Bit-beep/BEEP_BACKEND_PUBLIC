package com.beep.beep.domain.teacher.exception.error;


import com.beep.beep.global.exception.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum TeacherErrorProperty implements ErrorProperty {
    JOB_NOT_FOUND(HttpStatus.NOT_FOUND, "직책을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String message;
}
