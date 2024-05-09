package com.beep.beep.domain.teacher.presentation.dto;


import lombok.Getter;

@Getter
public class Job {

    private Long userIdx;

    private String department; // 소속부서

    private String job; // 직책
}
