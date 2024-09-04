package com.beep.beep.domain.student.presentation.dto.response;

import java.time.LocalDateTime;

public record AttendListRes(String name, Integer grade, Integer cls, Integer num, boolean isExist, LocalDateTime modifiedDate) {
}
