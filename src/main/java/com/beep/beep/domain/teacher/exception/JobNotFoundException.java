package com.beep.beep.domain.teacher.exception;


import com.beep.beep.domain.teacher.exception.error.TeacherErrorProperty;
import com.beep.beep.domain.user.exception.error.UserErrorProperty;
import com.beep.beep.global.exception.BusinessException;

public class JobNotFoundException extends BusinessException {
    public static final JobNotFoundException EXCEPTION = new JobNotFoundException();

    private JobNotFoundException(){
        super(TeacherErrorProperty.JOB_NOT_FOUND);
    }
}
