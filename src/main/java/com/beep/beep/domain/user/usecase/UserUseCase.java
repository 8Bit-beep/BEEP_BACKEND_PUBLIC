package com.beep.beep.domain.user.usecase;

import com.beep.beep.domain.student.domain.Student;
import com.beep.beep.domain.student.service.StudentService;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.repo.UserJpaRepo;
import com.beep.beep.domain.user.exception.UserNotFoundException;
import com.beep.beep.domain.user.presentation.dto.request.ChangePwReq;
import com.beep.beep.global.common.repository.UserSessionHolder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUseCase {

    private final UserSessionHolder userSessionHolder;
    private final PasswordEncoder encoder;
    private final UserJpaRepo userJpaRepo;
    private final StudentService studentService;

    public void withdrawal(){
        String email = userSessionHolder.getUser().email();
        userJpaRepo.deleteById(email);
        studentService.deleteById(email);
    }

    @Transactional
    public void changePw(ChangePwReq req){
        User user = userJpaRepo.findById(req.email())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
        user.updatePassword(encoder.encode(req.password()));
    }

}
