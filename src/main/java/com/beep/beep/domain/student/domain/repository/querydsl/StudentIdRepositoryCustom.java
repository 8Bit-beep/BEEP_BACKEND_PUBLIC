package com.beep.beep.domain.student.domain.repository.querydsl;

import com.beep.beep.domain.student.domain.StudentIdEntity;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentIdRepositoryCustom {

    List<StudentIdEntity> findByGradeAndCls(int grade,int cls);

//    List<Integer> findClsByGrade(@Param("grade") int grade);
//
//    int countByCls(@Param("grade") int grade,@Param("cls") int cls);

}
