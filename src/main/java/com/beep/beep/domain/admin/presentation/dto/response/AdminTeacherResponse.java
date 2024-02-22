package com.beep.beep.domain.admin.presentation.dto.response;

import com.beep.beep.domain.teacher.domain.Job;
import com.beep.beep.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
@AllArgsConstructor
public class AdminTeacherResponse {

    private Long idx;
    private String id;
    private String firstname;
    private String lastname;
    private String email;

    private String department;
    private String job;

    public static AdminTeacherResponse of(User user, Job job) {
        return AdminTeacherResponse.builder()
                .idx(user.getIdx())
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .department(job.getDepartment())
                .job(job.getJob()).build();
    }

}
