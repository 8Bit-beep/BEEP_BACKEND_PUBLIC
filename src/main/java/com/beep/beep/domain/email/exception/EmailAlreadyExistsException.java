package com.beep.beep.domain.email.exception;

import com.beep.beep.domain.email.exception.error.EmailErrorProperty;
import com.beep.beep.global.exception.BusinessException;

public class EmailAlreadyExistsException extends BusinessException {
    public static final EmailAlreadyExistsException EXCEPTION = new EmailAlreadyExistsException();

    private EmailAlreadyExistsException(){
        super(EmailErrorProperty.EMAIL_CONFLICT);
    }
}
