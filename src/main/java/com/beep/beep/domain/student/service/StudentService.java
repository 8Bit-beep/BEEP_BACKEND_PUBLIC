package com.beep.beep.domain.student.service;

import com.beep.beep.domain.room.domain.Room;
import com.beep.beep.domain.room.service.RoomService;
import com.beep.beep.domain.student.domain.Student;
import com.beep.beep.domain.student.domain.repository.StudentJpaRepo;
import com.beep.beep.domain.student.presentation.dto.request.StudentSignUpByExcel;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.repo.UserJpaRepo;
import com.beep.beep.domain.user.exception.UserAlreadyExistsException;
import com.beep.beep.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentJpaRepo studentJpaRepo;
    private final UserService userService;
    private final RoomService roomService;

    public void isExists(User user) {
        if(studentJpaRepo.existsByUser(user))
            throw UserAlreadyExistsException.EXCEPTION;
    }

    public void save(Student studentEntity) {
        studentJpaRepo.save(studentEntity);
    }

    public Student getStudent(User user) {
        return studentJpaRepo.findByUser(user);
    }

    public List<Student> getClassMemberList(Integer grade,Integer cls){
        return studentJpaRepo.findAllByGradeAndCls(grade,cls);
    }

    public List<Student> getStudyMemberList(Room room){
        return studentJpaRepo.findAllByStudyRoom(room);
    }

    public List<Student> getRoomAttendList(Room room){
        return studentJpaRepo.findAllByRoom(room);
    }

    public void deleteByUser(User user) {
        studentJpaRepo.deleteByUser(user);
    }


    public List<Student> findAll() {
        return studentJpaRepo.findAll();
    }

    public void updateByExcel(StudentSignUpByExcel req,User user,Room studyRoom) {
        studentJpaRepo.updateAll(req.grade(),req.cls(),req.num(),studyRoom,user);
    }
}
