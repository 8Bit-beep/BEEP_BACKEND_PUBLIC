package com.beep.beep.domain.student.domain.repository;

import com.beep.beep.domain.student.domain.StudentId;
import com.beep.beep.domain.student.domain.repository.querydsl.StudentIdRepoCustom;
import org.springframework.data.repository.CrudRepository;

public interface StudentIdRepository extends CrudRepository<StudentId,String> , StudentIdRepoCustom {
}
