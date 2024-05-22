package com.beep.beep.domain.beep.domain;

import com.beep.beep.domain.student.domain.StudentIdEntity;
import com.beep.beep.domain.user.domain.UserEntity;
import com.beep.beep.domain.user.presentation.dto.User;
import com.beep.beep.global.common.entity.BaseTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Entity
@Getter
@Table(name = "tb_attendance")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class AttendanceEntity extends BaseTime {
    @Id
    private Long userIdx;

    @Column(unique = true,nullable = false)
    private String code;

}
