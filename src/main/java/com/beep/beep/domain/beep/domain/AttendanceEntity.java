package com.beep.beep.domain.beep.domain;

import com.beep.beep.global.common.entity.BaseTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Entity
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_attendance")
public class AttendanceEntity extends BaseTime {
    @Id
    private Long userIdx;

    @Column(unique = true,nullable = false)
    private String code;

    @Builder
    public AttendanceEntity(Long userIdx, String code) {
        this.userIdx = userIdx;
        this.code = code;
    }
}
