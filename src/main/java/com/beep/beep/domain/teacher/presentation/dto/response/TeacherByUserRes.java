package com.beep.beep.domain.teacher.presentation.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder @AllArgsConstructor
public class TeacherByUserRes {
    private String name;
    private String email;

    private String department;
    private String job;

}
