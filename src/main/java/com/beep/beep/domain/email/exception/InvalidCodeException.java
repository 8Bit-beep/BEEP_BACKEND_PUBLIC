package com.beep.beep.domain.email.exception;

import com.beep.beep.domain.email.exception.error.EmailErrorProperty;
import com.beep.beep.global.exception.BusinessException;

public class InvalidCodeException extends BusinessException {
    public static final InvalidCodeException EXCEPTION = new InvalidCodeException();

    private InvalidCodeException(){
        super(EmailErrorProperty.INVALID_CODE);
    }
}
