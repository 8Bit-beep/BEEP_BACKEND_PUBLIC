package com.beep.beep.domain.teacher.domain;


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
@Getter @SuperBuilder
@Table(name = "tb_job")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JobEntity {

    @Id
    private Long userIdx;

    @Column(nullable = false)
    private String department; // 소속부서

    @Column(nullable = false)
    private String job; // 직책

}
