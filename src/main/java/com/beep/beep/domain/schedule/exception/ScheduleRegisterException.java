package com.beep.beep.domain.schedule.exception;

import com.beep.beep.domain.schedule.exception.error.ScheduleErrorProperty;
import com.beep.beep.global.exception.BusinessException;

public class ScheduleRegisterException extends BusinessException {
    public static final ScheduleRegisterException EXCEPTION = new ScheduleRegisterException();

    private ScheduleRegisterException(){
        super(ScheduleErrorProperty.REGISTER_FAILED);
    }

}
