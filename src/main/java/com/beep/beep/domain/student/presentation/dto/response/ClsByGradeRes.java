package com.beep.beep.domain.student.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ClsByGradeRes {
    private Integer cls;
    private Long headCount;
}
