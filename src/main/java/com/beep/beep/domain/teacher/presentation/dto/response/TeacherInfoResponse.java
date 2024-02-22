package com.beep.beep.domain.teacher.presentation.dto.response;


import com.beep.beep.domain.teacher.domain.Job;
import com.beep.beep.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder @AllArgsConstructor
public class TeacherInfoResponse {
    private String firstname;
    private String lastname;
    private String email;

    private String department;
    private String job;

    public static TeacherInfoResponse of(User user, Job job) {
        return TeacherInfoResponse.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .department(job.getDepartment())
                .job(job.getJob()).build();
    }

}
