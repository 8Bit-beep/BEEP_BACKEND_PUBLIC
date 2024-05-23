package com.beep.beep.domain.teacher.mapper;

import com.beep.beep.domain.teacher.domain.Job;
import com.beep.beep.domain.teacher.presentation.dto.request.SaveJobReq;
import com.beep.beep.domain.user.presentation.dto.UserVO;
import org.springframework.stereotype.Component;


@Component
public class TeacherMapper {

    public Job toJob(UserVO userVO, SaveJobReq request) {
        return Job.builder()
                .job(request.getJob())
                .department(request.getDepartment())
                .userIdx(userVO.getIdx()).build();
    }

}
