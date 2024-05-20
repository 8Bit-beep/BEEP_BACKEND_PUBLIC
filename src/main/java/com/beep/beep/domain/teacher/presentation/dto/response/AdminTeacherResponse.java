package com.beep.beep.domain.teacher.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
@AllArgsConstructor
public class AdminTeacherResponse {

    private Long idx;
    private String id;
    private String name;
    private String email;

    private String department;
    private String job;

}
