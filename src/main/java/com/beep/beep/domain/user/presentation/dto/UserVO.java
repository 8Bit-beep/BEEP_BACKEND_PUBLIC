package com.beep.beep.domain.user.presentation.dto;


import com.beep.beep.domain.user.domain.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVO {

    private Long idx;

    private String email;

    private String id;

    private String name;

    private String password;

    private UserType authority;

}
