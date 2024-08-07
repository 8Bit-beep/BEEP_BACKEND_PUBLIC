package com.beep.beep.domain.user.usecase;

import com.beep.beep.domain.student.domain.Student;
import com.beep.beep.domain.student.service.StudentService;
import com.beep.beep.domain.user.presentation.dto.request.ChangePwReq;
import com.beep.beep.global.common.repository.UserSessionHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUseCase {

    private final StudentService studentService;
    private final UserSessionHolder userSessionHolder;
    private final PasswordEncoder encoder;

    public void withdrawal(){
        studentService.deleteById(userSessionHolder.getUser().id());
    }

    public void changePw(ChangePwReq req){
        Student student = studentService.findByEmail(userSessionHolder.getUser().email());
        student.updatePassword(encoder.encode(req.password()));
    }

}
