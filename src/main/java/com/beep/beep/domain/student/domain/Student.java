package com.beep.beep.domain.student.domain;

import com.beep.beep.global.common.entity.BaseTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@Table(name = "tb_student")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer grade;
    private Integer cls;
    private Integer num;
    private String code;
    private String studyCode;

    @Column(nullable = false,unique = true)
    private String username;

    public void updateCode(String code){
        this.code = code;
    }

}
