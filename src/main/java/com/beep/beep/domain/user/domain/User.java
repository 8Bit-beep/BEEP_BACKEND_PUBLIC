package com.beep.beep.domain.user.domain;

import com.beep.beep.domain.student.presentation.dto.request.StudentSignUpReq;
import com.beep.beep.domain.user.domain.enums.RoomCode;
import com.beep.beep.domain.user.domain.enums.UserType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

import static com.beep.beep.domain.user.domain.enums.RoomCode.NOTFOUND;

@Entity
@Getter
@Setter
@SuperBuilder
@Table(name = "tb_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @Column(unique = true,nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType authority;

    private LocalDateTime lastUpdated;

    private Integer grade;
    private Integer cls;
    private Integer num;

    @Enumerated(EnumType.STRING)
    private RoomCode currentRoom;

    @Enumerated(EnumType.STRING)
    private RoomCode fixedRoom;

    public void updatePassword(String password) {
        this.password = password;
    }

    public void saveStudentInfo(StudentSignUpReq req) {
        this.grade = req.grade();
        this.cls = req.cls();
        this.num = req.num();
        this.currentRoom = RoomCode.of("0");
    }
}
