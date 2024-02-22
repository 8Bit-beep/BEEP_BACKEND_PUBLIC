package com.beep.beep.domain.teacher.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_job")
public class Job {
    @Id
    private Long userIdx;

    private String department; // 소속부서
    private String job; // 직책

    @Builder
    public Job(Long userIdx,String job,String department){
        this.userIdx = userIdx;
        this.department = department;
        this.job = job;
    }
}
