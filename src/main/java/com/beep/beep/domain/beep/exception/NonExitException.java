package com.beep.beep.domain.beep.exception;


import com.beep.beep.domain.beep.exception.error.BeepErrorProperty;
import com.beep.beep.global.exception.BusinessException;

public class NonExitException extends BusinessException {
    public static final NonExitException EXCEPTION = new NonExitException();

    private NonExitException(){
        super(BeepErrorProperty.NOT_EXITED);
    }

}
