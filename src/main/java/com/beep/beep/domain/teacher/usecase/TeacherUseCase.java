package com.beep.beep.domain.teacher.usecase;

import com.beep.beep.domain.teacher.presentation.dto.response.TeacherInfoRes;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.repo.UserJpaRepo;
import com.beep.beep.domain.user.exception.UserNotFoundException;
import com.beep.beep.global.common.repository.UserSessionHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherUseCase {

    private final UserSessionHolder userSessionHolder;
    private final UserJpaRepo userJpaRepo;

    public TeacherInfoRes teacherInfo() {
        User user = userJpaRepo.findById(userSessionHolder.getUser().email())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        return TeacherInfoRes.of(user);
    }

}
