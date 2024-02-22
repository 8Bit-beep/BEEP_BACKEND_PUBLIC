package com.beep.beep.global.exception;



import com.beep.beep.global.exception.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BusinessException extends RuntimeException {
    private final ErrorProperty error;
}
