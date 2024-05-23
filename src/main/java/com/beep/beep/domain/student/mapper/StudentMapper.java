package com.beep.beep.domain.student.mapper;

import com.beep.beep.domain.student.domain.StudentId;
import com.beep.beep.domain.student.presentation.dto.request.SaveStudentIdReq;
import com.beep.beep.domain.user.presentation.dto.UserVO;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    public StudentId toStudentId(UserVO userVO, SaveStudentIdReq req) {
        return StudentId.builder()
                .userIdx(userVO.getIdx())
                .grade(req.getGrade())
                .cls(req.getCls())
                .num(req.getNum()).build();
    }
}
