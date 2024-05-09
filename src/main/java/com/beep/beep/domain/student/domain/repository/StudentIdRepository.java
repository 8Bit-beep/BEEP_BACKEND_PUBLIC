package com.beep.beep.domain.student.domain.repository;


import com.beep.beep.domain.student.domain.StudentIdEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface StudentIdRepository extends CrudRepository<StudentIdEntity,String> {

    StudentIdEntity findByUserIdx(Long userIdx);

    @Query("SELECT s FROM StudentIdEntity s WHERE s.grade = :grade AND s.cls = :cls")
    List<StudentIdEntity> findByGradeAndCls(@Param("grade") int grade, @Param("cls") int cls);

    @Query("SELECT DISTINCT s.cls FROM StudentIdEntity s WHERE s.grade = :grade")
    List<Integer> findClsByGrade(@Param("grade") int grade);

    @Query("SELECT COUNT(s) FROM StudentIdEntity s WHERE s.grade = :grade AND s.cls = :cls")
    int countByCls(@Param("grade") int grade,@Param("cls") int cls);

}
