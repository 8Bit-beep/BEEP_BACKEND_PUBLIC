package com.beep.beep.domain.student.presentation.dto.request;

import lombok.Getter;

@Getter
public class SaveStudentIdReq {
    private String email;

    private Integer grade;
    private Integer cls;
    private Integer num;
}
