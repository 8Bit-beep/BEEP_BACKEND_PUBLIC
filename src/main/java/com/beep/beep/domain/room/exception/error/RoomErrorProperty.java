package com.beep.beep.domain.room.exception.error;



import com.beep.beep.global.exception.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum RoomErrorProperty implements ErrorProperty {
    FAILED(HttpStatus.INTERNAL_SERVER_ERROR,"실 리스트 조회 실패");

    private final HttpStatus status;
    private final String message;
}
