package com.beep.beep.domain.schedule.exception.error;



import com.beep.beep.global.exception.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ScheduleErrorProperty implements ErrorProperty {
    REGISTER_FAILED(HttpStatus.INTERNAL_SERVER_ERROR,"일정 업로드 실패");

    private final HttpStatus status;
    private final String message;
}
