package com.beep.beep.domain.room.exception;

import com.beep.beep.domain.room.exception.error.RoomErrorProperty;
import com.beep.beep.global.exception.BusinessException;

public class GetRoomListException extends BusinessException {
    public static final GetRoomListException EXCEPTION = new GetRoomListException();

    private GetRoomListException(){
        super(RoomErrorProperty.FAILED);
    }

}
