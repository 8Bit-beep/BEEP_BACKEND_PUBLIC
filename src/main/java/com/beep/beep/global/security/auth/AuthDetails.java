package com.beep.beep.global.security.auth;

import com.beep.beep.domain.user.presentation.dto.UserVO;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
public class AuthDetails implements UserDetails {

    private final UserVO userVO;

    public AuthDetails(final UserVO userVO) {
        this.userVO = userVO;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton((GrantedAuthority) userVO.authority()::getAuthority);
    }

    @Override
    public String getPassword() {
        return this.userVO.password();
    }

    @Override
    public String getUsername() {
        return this.userVO.email();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}
