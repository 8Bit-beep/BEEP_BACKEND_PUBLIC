package com.beep.beep.domain.student.presentation.dto.request;

import com.beep.beep.domain.room.domain.Club;

/**
 * 학생 정보 저장 req
 * */
public record StudentSignUpReq(
        Integer grade,
        Integer cls,
        Integer num,
        String email,
        Club club) {
}
