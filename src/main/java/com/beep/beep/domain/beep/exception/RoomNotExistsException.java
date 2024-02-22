package com.beep.beep.domain.beep.exception;


import com.beep.beep.domain.beep.exception.error.BeepErrorProperty;
import com.beep.beep.global.exception.BusinessException;

public class RoomNotExistsException extends BusinessException {
    public static final RoomNotExistsException EXCEPTION = new RoomNotExistsException();

    private RoomNotExistsException(){
        super(BeepErrorProperty.ROOM_NOT_FOUND);
    }

}
