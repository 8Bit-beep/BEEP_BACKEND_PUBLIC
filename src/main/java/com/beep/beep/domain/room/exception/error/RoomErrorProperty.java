package com.beep.beep.domain.room.exception.error;



import com.beep.beep.global.exception.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum RoomErrorProperty implements ErrorProperty {
    ROOM_NOT_FOUND(HttpStatus.NOT_FOUND,"실을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String message;
}
