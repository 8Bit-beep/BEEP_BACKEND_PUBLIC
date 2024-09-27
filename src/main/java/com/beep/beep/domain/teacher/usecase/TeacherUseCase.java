package com.beep.beep.domain.teacher.usecase;

import com.beep.beep.domain.teacher.presentation.dto.response.TeacherInfoRes;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.repo.UserJpaRepo;
import com.beep.beep.domain.user.exception.UserNotFoundException;
import com.beep.beep.domain.user.presentation.dto.UserVO;
import com.beep.beep.domain.user.service.UserService;
import com.beep.beep.global.common.dto.response.ResponseData;
import com.beep.beep.global.common.repository.UserSessionHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherUseCase {

    private final UserSessionHolder userSessionHolder;

    public ResponseData<TeacherInfoRes> teacherInfo() {
        TeacherInfoRes result = TeacherInfoRes.of(userSessionHolder.getUser());
        return ResponseData.ok("선생님프로필 조회 성공",result);
    }

}
