package com.beep.beep.domain.beep.exception;


import com.beep.beep.domain.beep.exception.error.BeepErrorProperty;
import com.beep.beep.global.exception.BusinessException;

public class NotCurrentRoomException extends BusinessException {
    public static final NotCurrentRoomException EXCEPTION = new NotCurrentRoomException();

    private NotCurrentRoomException(){
        super(BeepErrorProperty.NOT_CURRENT_ROOM);
    }

}
