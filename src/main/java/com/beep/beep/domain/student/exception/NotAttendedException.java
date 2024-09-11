package com.beep.beep.domain.student.exception;

import com.beep.beep.domain.student.exception.error.StudentErrorProperty;
import com.beep.beep.global.exception.BusinessException;

public class NotAttendedException extends BusinessException {
    public static final NotAttendedException EXCEPTION = new NotAttendedException();

    private NotAttendedException(){
        super(StudentErrorProperty.NOT_ATTENDED);
    }

}
