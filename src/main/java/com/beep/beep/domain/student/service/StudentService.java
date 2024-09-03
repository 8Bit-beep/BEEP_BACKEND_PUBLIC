package com.beep.beep.domain.student.service;

import com.beep.beep.domain.student.domain.Student;
import com.beep.beep.domain.student.domain.repository.StudentJpaRepo;
import com.beep.beep.domain.student.presentation.dto.response.AttendListRes;
import com.beep.beep.domain.student.presentation.dto.response.MemberListRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentJpaRepo studentJpaRepo;

    public void save(Student studentEntity) {
        studentJpaRepo.save(studentEntity);
    }

    public Student findByEmail(String email) {
        return studentJpaRepo.findByUsername(email);
    }

    public List<AttendListRes> attendList(String code){
        return studentJpaRepo.attendList(code);
    }

    public List<MemberListRes> memberList(Integer grade,Integer cls){
        return studentJpaRepo.memberList(grade,cls);
    }

    public void deleteById(String email) {
        studentJpaRepo.deleteByUsername(email);
    }


}
