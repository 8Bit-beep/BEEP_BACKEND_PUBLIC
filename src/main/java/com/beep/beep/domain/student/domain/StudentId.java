package com.beep.beep.domain.student.domain;


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
@Table(name = "tb_student_id")
public class StudentId {

    @Id
    private Long userIdx;

    @Column(nullable = false)
    private int cls;
    private int grade;
    private int num;

    @Builder
    public StudentId(Long userIdx,int cls,int grade,int num){
        this.userIdx = userIdx;
        this.cls = cls;
        this.grade = grade;
        this.num = num;
    }
}
