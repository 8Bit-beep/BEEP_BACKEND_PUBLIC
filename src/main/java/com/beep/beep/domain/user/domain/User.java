package com.beep.beep.domain.user.domain;


import com.beep.beep.domain.user.domain.enums.UserType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(unique = true,nullable = false)
    private String id;
    private String email;

    @Column(nullable = false)
    private String password;
    private String firstname;
    private String lastname;


    @Enumerated(EnumType.STRING)
    private UserType authority;

    @Builder
    public User(String id, String password,String lastname,String firstname, String email, UserType authority){
        this.id = id;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.authority = authority;
    }

    public void updateUser(String password) {
        this.password = password.isBlank() ? this.password : password;
    }

}
