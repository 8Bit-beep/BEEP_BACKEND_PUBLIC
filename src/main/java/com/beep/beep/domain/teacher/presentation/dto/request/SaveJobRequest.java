package com.beep.beep.domain.teacher.presentation.dto.request;

import lombok.Getter;

@Getter
public class SaveJobRequest {
    private String email;
    private String job;
    private String department;
}
