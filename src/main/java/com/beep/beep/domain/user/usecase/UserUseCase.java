package com.beep.beep.domain.user.usecase;

import com.beep.beep.domain.student.service.StudentService;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.repo.UserJpaRepo;
import com.beep.beep.domain.user.exception.UserNotFoundException;
import com.beep.beep.domain.user.presentation.dto.request.ChangePwReq;
import com.beep.beep.domain.user.service.UserService;
import com.beep.beep.global.common.dto.response.Response;
import com.beep.beep.global.common.repository.UserSessionHolder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUseCase {

    private final PasswordEncoder encoder;
    private final UserService userService;
    private final StudentService studentService;

    public Response withdrawal(){
        String email = userService.getCurrentEmail();
        userService.deleteUser(email);
        studentService.deleteById(email);
        return Response.ok("회원탈퇴 성공");
    }

    @Transactional
    public Response changePw(ChangePwReq req){
        User user = userService.findByEmail(req.email());
        user.updatePassword(encoder.encode(req.password()));
        return Response.ok("비밀번호 변경 성공");
    }

}
