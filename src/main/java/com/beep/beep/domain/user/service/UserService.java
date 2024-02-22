package com.beep.beep.domain.user.service;


import com.beep.beep.domain.student.facade.StudentFacade;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.facade.UserFacade;
import com.beep.beep.domain.user.presentation.dto.request.ChangePwRequest;
import com.beep.beep.domain.user.presentation.dto.response.UserIdResponse;
import com.beep.beep.global.security.jwt.JwtProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserFacade userFacade;
    private final StudentFacade studentIdFacade;
    private final PasswordEncoder encoder;
    private final JwtProvider jwtProvider;

    public UserIdResponse findId(String email) {
        String id = userFacade.findIdByEmail(email);

        return UserIdResponse.builder()
                .id(id).build();
    }

    public void checkIdEmail(String id,String email) {
        userFacade.existsByIdAndEmail(id,email);
    }

    @Transactional
    public void changePw(ChangePwRequest request){
        User user = userFacade.findUserById(request.getId());

        user.updateUser(encoder.encode(request.getPassword()));
    }


}
