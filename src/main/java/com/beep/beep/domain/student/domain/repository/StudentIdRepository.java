package com.beep.beep.domain.student.domain.repository;


import com.beep.beep.domain.student.domain.StudentId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface StudentIdRepository extends CrudRepository<StudentId,String> {

    StudentId findByUserIdx(Long userIdx);

    @Query("SELECT s FROM StudentId s WHERE s.grade = :grade AND s.cls = :cls")
    List<StudentId> findByGradeAndCls(@Param("grade") int grade, @Param("cls") int cls);

    @Query("SELECT DISTINCT s.cls FROM StudentId s WHERE s.grade = :grade")
    List<Integer> findClsByGrade(@Param("grade") int grade);

    @Query("SELECT COUNT(s) FROM StudentId s WHERE s.grade = :grade AND s.cls = :cls")
    int countByCls(@Param("grade") int grade,@Param("cls") int cls);

}
