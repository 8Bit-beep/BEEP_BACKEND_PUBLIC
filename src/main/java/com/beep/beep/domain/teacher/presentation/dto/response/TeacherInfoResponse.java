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

    public static TeacherInfoResponse of(User user, Job job) {
        return TeacherInfoResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .department(job.getDepartment())
                .job(job.getJob()).build();
    }

}
