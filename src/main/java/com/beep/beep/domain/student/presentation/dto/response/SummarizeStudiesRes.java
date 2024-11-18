package com.beep.beep.domain.student.presentation.dto.response;

import com.beep.beep.domain.room.domain.Club;

public record SummarizeStudiesRes(
        Club clubName,
        Integer attendedStudent,
        Integer totalStudent
) {
}
