package com.beep.beep.domain.user.domain.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserType {
    ROLE_STUDENT("STUDENT"),
    ROLE_TEACHER("TEACHER"),
    ROLE_ADMIN("ADMIN");

    private final String authority;
}
