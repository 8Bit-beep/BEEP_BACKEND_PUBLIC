package com.beep.beep.domain.student.service;

import com.beep.beep.domain.student.domain.Student;
import com.beep.beep.domain.student.domain.enums.RoomCode;
import com.beep.beep.domain.student.domain.repository.StudentJpaRepo;
import com.beep.beep.domain.student.presentation.dto.request.MemberListReq;
import com.beep.beep.domain.student.presentation.dto.response.AttendListRes;
import com.beep.beep.domain.student.presentation.dto.response.MemberListRes;
import com.beep.beep.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentJpaRepo studentJpaRepo;

    public Student findByEmail(String email) {
        return studentJpaRepo.findByEmail(email).orElseThrow(
                () -> UserNotFoundException.EXCEPTION);
    }

    public List<AttendListRes> attendList(RoomCode code){
        return studentJpaRepo.attendList(code);
    }

    public List<MemberListRes> memberList(MemberListReq req){
        return studentJpaRepo.memberList(req);
    }

    public void deleteById(Long id) {
        studentJpaRepo.deleteById(id);
    }

}
