package com.beep.beep.domain.teacher.usecase;

import com.beep.beep.domain.teacher.presentation.dto.response.TeacherInfoRes;
import com.beep.beep.domain.teacher.service.TeacherService;
import com.beep.beep.global.common.repository.UserSessionHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherUseCase {

    private final UserSessionHolder userSessionHolder;
    private final TeacherService teacherService;

    public TeacherInfoRes teacherInfo() {
        return TeacherInfoRes.of(teacherService.findByEmail(userSessionHolder.getUser().email()));
    }

}
