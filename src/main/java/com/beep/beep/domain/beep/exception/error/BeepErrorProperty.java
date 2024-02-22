package com.beep.beep.domain.beep.exception.error;



import com.beep.beep.global.exception.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BeepErrorProperty implements ErrorProperty {
    ROOM_NOT_FOUND(HttpStatus.NOT_FOUND,"실을 찾을 수 없습니다."),
    NOT_EXITED(HttpStatus.CONFLICT,"퇴실하지 않았습니다."),
    NOT_CURRENT_ROOM(HttpStatus.BAD_REQUEST,"입실한 실이 아닙니다.");

    private final HttpStatus status;
    private final String message;
}
