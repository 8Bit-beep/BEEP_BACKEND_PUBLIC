package com.beep.beep.domain.student.exception;

import com.beep.beep.domain.student.exception.error.StudentErrorProperty;
import com.beep.beep.global.exception.BusinessException;

public class NotAllowedAttendException extends BusinessException {
    public static final NotAllowedAttendException EXCEPTION = new NotAllowedAttendException();

    private NotAllowedAttendException(){
        super(StudentErrorProperty.NOT_ALLOWED_ATTEND);
    }

}
