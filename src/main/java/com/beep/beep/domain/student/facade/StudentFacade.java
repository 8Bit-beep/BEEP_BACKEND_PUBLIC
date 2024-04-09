package com.beep.beep.domain.student.facade;


import com.beep.beep.domain.student.domain.StudentId;
import com.beep.beep.domain.student.domain.repository.StudentIdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentFacade {

    private final StudentIdRepository studentIdRepository;

    public StudentId findByUserIdx(Long userIdx) {
        return studentIdRepository.findByUserIdx(userIdx);
    }

    public List<StudentId> findByGradeCls(int grade, int cls){
        return studentIdRepository.findByGradeAndCls(grade,cls);
    }

    public List<Integer> findAllClsByGrade(int grade){
        return studentIdRepository.findClsByGrade(grade);
    }

    public int countStudentsByCls(int grade,int cls){
        return studentIdRepository.countByCls(grade,cls);
    }

    public void save(StudentId studentId){
        studentIdRepository.save(studentId);
    }


}