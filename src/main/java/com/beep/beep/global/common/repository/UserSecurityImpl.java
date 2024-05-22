package com.beep.beep.global.common.repository;

import com.beep.beep.domain.user.presentation.dto.User;
import com.beep.beep.global.security.auth.AuthDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UserSecurityImpl implements UserSecurity{

    @Override
    public User getUser() {
        System.out.println("십것");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            System.out.println("Authentication is null");
            return null;
        }

        Object principal = authentication.getPrincipal();
        if (principal == null) {
            System.out.println("Principal is null");
            return null;
        }

        if (!(principal instanceof AuthDetails)) {
            System.out.println("Principal is not an instance of AuthDetails");
            return null;
        }

        AuthDetails authDetails = (AuthDetails) principal;
        User user = authDetails.getUser();
        if (user == null) {
            System.out.println("User in AuthDetails is null");
        }
        System.out.println(user.getIdx());
        return user;
    }

}
