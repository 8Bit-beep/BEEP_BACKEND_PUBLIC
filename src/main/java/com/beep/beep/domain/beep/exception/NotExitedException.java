package com.beep.beep.domain.beep.exception;


import com.beep.beep.domain.beep.exception.error.BeepErrorProperty;
import com.beep.beep.global.exception.BusinessException;

public class NotExitedException extends BusinessException {
    public static final NotExitedException EXCEPTION = new NotExitedException();

    private NotExitedException(){
        super(BeepErrorProperty.NOT_EXITED);
    }

}
