package com.beep.beep.domain.room.exception;

import com.beep.beep.domain.room.exception.error.RoomErrorProperty;
import com.beep.beep.global.exception.BusinessException;

public class RoomNotFoundException extends BusinessException {
    public static final RoomNotFoundException EXCEPTION = new RoomNotFoundException();

    private RoomNotFoundException(){
        super(RoomErrorProperty.ROOM_NOT_FOUND);
    }

}
