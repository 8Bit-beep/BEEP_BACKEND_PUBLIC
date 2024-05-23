package com.beep.beep.domain.beep.domain;

import com.beep.beep.global.common.entity.BaseTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Entity
@Getter
@Table(name = "tb_attendance")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class Attendance extends BaseTime {
    @Id
    private Long userIdx;

    @Column(unique = true,nullable = false)
    private String code;

}
