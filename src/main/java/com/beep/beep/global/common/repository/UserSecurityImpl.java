package com.beep.beep.global.common.repository;

import com.beep.beep.domain.user.presentation.dto.UserVO;
import com.beep.beep.global.security.auth.AuthDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UserSecurityImpl implements UserSecurity{

    @Override
    public UserVO getUser() {
        return ((AuthDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getUserVO();
    }

}
