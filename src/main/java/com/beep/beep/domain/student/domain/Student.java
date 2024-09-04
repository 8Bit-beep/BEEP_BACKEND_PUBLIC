package com.beep.beep.domain.student.domain;

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
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@SuperBuilder
@Table(name = "tb_student")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {

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

    @LastModifiedDate
    protected LocalDateTime modifiedDate;

    public void updateCode(String code){
        this.code = code;
    }
    public void updateDate(){
        this.modifiedDate = LocalDateTime.now();
    }

}
