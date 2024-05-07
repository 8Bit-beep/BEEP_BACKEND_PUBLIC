package com.beep.beep.domain.student.domain;


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
@SuperBuilder
@Table(name = "tb_student_id")
public class StudentId {

    @Id
    private Long userIdx;

    @Column(nullable = false)
    private int cls;
    private int grade;
    private int num;
}
