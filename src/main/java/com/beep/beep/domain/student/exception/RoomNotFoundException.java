package com.beep.beep.domain.student.exception;

import com.beep.beep.domain.student.exception.error.StudentErrorProperty;
import com.beep.beep.global.exception.BusinessException;

public class RoomNotFoundException extends BusinessException {
    public static final RoomNotFoundException EXCEPTION = new RoomNotFoundException();

    private RoomNotFoundException(){
        super(StudentErrorProperty.ROOM_NOT_FOUND);
    }

}
