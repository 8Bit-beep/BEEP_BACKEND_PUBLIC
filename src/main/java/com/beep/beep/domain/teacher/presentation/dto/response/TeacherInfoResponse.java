package com.beep.beep.domain.teacher.presentation.dto.response;


import com.beep.beep.domain.teacher.domain.Job;
import com.beep.beep.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder @AllArgsConstructor
public class TeacherInfoResponse {
    private String name;
    private String email;

    private String department;
    private String job;

}
