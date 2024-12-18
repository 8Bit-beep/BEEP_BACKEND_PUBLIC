package com.beep.beep.domain.user.exception;


import com.beep.beep.domain.user.exception.error.UserErrorProperty;
import com.beep.beep.global.exception.BusinessException;

public class StudentIdAlreadyExistsException extends BusinessException {
    public static final StudentIdAlreadyExistsException EXCEPTION = new StudentIdAlreadyExistsException();

    private StudentIdAlreadyExistsException(){
        super(UserErrorProperty.STUDENT_ID_CONFLICT);
    }

}
