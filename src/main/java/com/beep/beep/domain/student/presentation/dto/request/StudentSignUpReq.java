package com.beep.beep.domain.student.presentation.dto.request;

import com.beep.beep.domain.room.domain.Club;

public record StudentSignUpReq(
        Integer grade,
        Integer cls,
        Integer num,
        String email,
        Club club) {
}
