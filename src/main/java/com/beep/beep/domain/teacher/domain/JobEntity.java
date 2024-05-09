package com.beep.beep.domain.teacher.domain;


import jakarta.persistence.Entity;
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

    private String department; // 소속부서
    private String job; // 직책
}
