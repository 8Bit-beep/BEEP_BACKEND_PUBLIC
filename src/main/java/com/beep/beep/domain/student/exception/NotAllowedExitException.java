package com.beep.beep.domain.student.exception;

import com.beep.beep.domain.student.exception.error.StudentErrorProperty;
import com.beep.beep.global.exception.BusinessException;

public class NotAllowedExitException extends BusinessException {
    public static final NotAllowedExitException EXCEPTION = new NotAllowedExitException();

    private NotAllowedExitException(){
        super(StudentErrorProperty.NOT_ALLOWED_EXIT);
    }

}
