package com.beep.beep.domain.beep.domain;

import com.beep.beep.global.entity.BaseTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_attendance")
public class Attendance extends BaseTime {
    @Id
    private Long userIdx;

    @Column(unique = true,nullable = false)
    private String code;

    @Builder
    public Attendance(Long userIdx,String code){
        this.userIdx = userIdx;
        this.code = code;
    }

    public void updateAttendance(String code) {
        this.code = code.isBlank() ? this.code : code;
    }
}
