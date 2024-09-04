package com.beep.beep.domain.student.domain.repository;

import com.beep.beep.domain.student.domain.Student;
import com.beep.beep.domain.student.domain.repository.query.CustomStudentRepo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentJpaRepo extends JpaRepository<Student, Long> , CustomStudentRepo {
    Student findByUsername(String email);
    void deleteByUsername(String email);
}
