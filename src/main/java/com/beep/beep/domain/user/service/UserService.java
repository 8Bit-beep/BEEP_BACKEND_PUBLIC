package com.beep.beep.domain.user.service;

import com.beep.beep.domain.user.domain.repository.UserRepo;
import com.beep.beep.domain.user.exception.UserAlreadyExistsException;
import com.beep.beep.domain.user.mapper.UserMapper;
import com.beep.beep.domain.user.presentation.dto.UserVO;
import com.beep.beep.domain.user.presentation.dto.request.ChangePwReq;
import com.beep.beep.domain.user.presentation.dto.response.UserIdRes;
import com.beep.beep.global.common.service.UserUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder encoder;
    private final UserRepo userRepository;
    private final UserUtil userUtil;
    private final UserMapper userMapper;

    public void withdrawal(){
        userRepository.deleteById(userUtil.getCurrentUser().getIdx());
    }

    public void idCheck(String id) {
        userUtil.existsById(id);
    }

    public UserIdRes findId(String email) {
        return UserIdRes.builder()
                .id(userUtil.findUserByEmail(email).getId()).build();
    }

    public void checkIdEmail(String id,String email) {
        if(!userRepository.existsByIdEmail(id,email))
            throw UserAlreadyExistsException.EXCEPTION;
    }

    @Transactional
    public void changePw(ChangePwReq request){
        UserVO userVO = userUtil.findUserByEmail(request.getEmail());
        userVO.setPassword(encoder.encode(request.getPassword()));
        userRepository.save(userMapper.toEdit(userVO));
    }

}
