package com.beep.beep.domain.email.exception;

import com.beep.beep.domain.email.exception.error.EmailErrorProperty;
import com.beep.beep.global.exception.BusinessException;

public class EmailNotFoundException extends BusinessException {
    public static final EmailNotFoundException EXCEPTION = new EmailNotFoundException();

    private EmailNotFoundException(){
        super(EmailErrorProperty.EMAIL_NOT_FOUND);
    }
}
