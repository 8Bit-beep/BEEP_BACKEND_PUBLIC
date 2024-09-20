package com.beep.beep.domain.user.usecase;

import com.beep.beep.domain.student.service.StudentService;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.exception.WithdrawalFailedException;
import com.beep.beep.domain.user.presentation.dto.request.ChangePwReq;
import com.beep.beep.domain.user.service.UserService;
import com.beep.beep.global.common.dto.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.beep.beep.domain.user.domain.enums.UserType.STUDENT;

@Component
@RequiredArgsConstructor
public class UserUseCase {

    private final PasswordEncoder encoder;
    private final UserService userService;
    private final StudentService studentService;

    @Transactional(rollbackFor = Exception.class)
    public Response withdrawal(){
        try {
            User user = userService.getUser();

            if (user.getAuthority() == STUDENT) {
                studentService.deleteByUser(user);
            }
            userService.deleteUser(user);

            return Response.ok("회원탈퇴 성공");
        } catch (Exception e) {
            throw WithdrawalFailedException.EXCEPTION;
        }
    }

    @Transactional
    public Response changePw(ChangePwReq req){
        User user = userService.findByEmail(req.email());
        user.updatePassword(encoder.encode(req.password()));
        return Response.ok("비밀번호 변경 성공");
    }

}
