package com.beep.beep.domain.student.presentation.dto.response;

import com.beep.beep.domain.user.presentation.dto.UserVO;

/**
 * 현재 학생 코드 반환
 * */
public record StudentCodeRes(String code) {
    public static StudentCodeRes of(UserVO user) {
            return new StudentCodeRes(user.currentRoom().getCode());
    }
}
