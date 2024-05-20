package com.beep.beep.global.common.service;

import com.beep.beep.domain.user.facade.UserFacade;
import com.beep.beep.domain.user.presentation.dto.User;
import com.beep.beep.global.common.repository.UserSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserUtil {

    private final UserFacade userFacade;
    private final UserSecurity userSecurity;

    public User getCurrentUser() {
        return userFacade.findUserByEmail(userSecurity.getUser().getEmail());
    }

}
