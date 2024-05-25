package com.beep.beep.domain.student.presentation.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder @AllArgsConstructor
public class StudentRes {
    private Long idx;
    private String id;
    private String name;
    private String email;

    private Integer grade;
    private Integer cls;
    private Integer num;

    private String code;
}
