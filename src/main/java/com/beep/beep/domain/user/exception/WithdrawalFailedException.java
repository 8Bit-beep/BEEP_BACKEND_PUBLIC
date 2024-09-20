package com.beep.beep.domain.user.exception;


import com.beep.beep.domain.user.exception.error.UserErrorProperty;
import com.beep.beep.global.exception.BusinessException;

public class WithdrawalFailedException extends BusinessException {
    public static final WithdrawalFailedException EXCEPTION = new WithdrawalFailedException();

    private WithdrawalFailedException(){
        super(UserErrorProperty.WITHDRAW_FAILED);
    }
}
