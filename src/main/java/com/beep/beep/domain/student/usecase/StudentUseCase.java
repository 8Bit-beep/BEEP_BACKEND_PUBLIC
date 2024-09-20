package com.beep.beep.domain.student.usecase;

import com.beep.beep.domain.room.domain.Club;
import com.beep.beep.domain.room.domain.Room;
import com.beep.beep.domain.room.service.RoomService;
import com.beep.beep.domain.student.domain.Student;
import com.beep.beep.domain.student.exception.NotAllowedExitException;
import com.beep.beep.domain.student.presentation.dto.request.AttendReq;
import com.beep.beep.domain.student.presentation.dto.request.StudentSignUpReq;
import com.beep.beep.domain.student.presentation.dto.response.AttendListRes;
import com.beep.beep.domain.student.presentation.dto.response.AttendRes;
import com.beep.beep.domain.student.presentation.dto.response.MemberListRes;
import com.beep.beep.domain.student.presentation.dto.response.StudentCodeRes;
import com.beep.beep.domain.student.presentation.dto.response.StudentInfoRes;
import com.beep.beep.domain.student.presentation.dto.response.StudyListRes;
import com.beep.beep.domain.student.service.StudentService;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.service.UserService;
import com.beep.beep.global.common.dto.response.Response;
import com.beep.beep.global.common.dto.response.ResponseData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.beep.beep.global.exception.error.ErrorCode.FORBIDDEN;

@Slf4j
@Component
@RequiredArgsConstructor
public class StudentUseCase {

    private final StudentService studentService;
    private final RoomService roomService;
    private final UserService userService;

    public Response signUp(StudentSignUpReq req) {
        User user = userService.findByEmail(req.email());
        studentService.isExists(user);

        studentService.save(req.toStudentEntity(user));
        return Response.created("학생 기본정보 저장 성공");
    }

    public ResponseData<StudentInfoRes> studentInfo() {
        User user = userService.getUser();
        StudentInfoRes result = StudentInfoRes.of(studentService.getStudent(user));
        return ResponseData.ok("학생 프로필 조회 성공",result);
    }

    public ResponseData<StudentCodeRes> studentCode() {
        User user = userService.getUser();
        StudentCodeRes result = StudentCodeRes.of(studentService.getStudent(user));
        return ResponseData.ok("학생 실 코드 조회 완료",result);
    }

    @Async
    @Transactional(rollbackFor = Exception.class)
    public CompletableFuture<ResponseData<AttendRes>> attend(AttendReq req) {
        User user = userService.getUser();
        ResponseData<AttendRes> attend = attendProcess(user, req);
        return CompletableFuture.completedFuture(attend);
    }

    // 트랜잭션이 적용된 동기 메서드
    @Transactional(rollbackFor = Exception.class)
    public ResponseData<AttendRes> attendProcess(User user,AttendReq req) {
        Student student = studentService.getStudent(user);
        Room lastRoom = student.getRoom();
        AttendRes res;

        // 룸 코드 확인
        Room notFound = Room.builder()
                .code("0")
                .name("미출석")
                .floor(0).build();
        Room room = roomService.findByCode(req.code()); // 이 코드는 존재하는가?

        if (lastRoom.getCode().equals("0")) { // 입실 처리
            student.updateRoom(notFound);
            student.updateDate();
            res = AttendRes.of(req.code());
        } else if (lastRoom.getCode().equals(req.code())) { // 퇴실 처리
            student.updateRoom(notFound);
            student.updateDate();
            res = AttendRes.of("");
        } else { // 퇴실 불가
            throw NotAllowedExitException.EXCEPTION;
        }

        return ResponseData.ok("출석 성공", res);
    }


    public ResponseData<List<AttendListRes>> attendList(String code) {
        Room room = roomService.findByCode(code);
        List<AttendListRes> result = AttendListRes.of(studentService.getRoomAttendList(room));
        return ResponseData.ok("출석부 조회 성공",result);
    }

    public ResponseData<List<MemberListRes>> memberList(Integer grade,Integer cls) {
        List<MemberListRes> result = MemberListRes.of(studentService.getClassMemberList(grade,cls));
        return ResponseData.ok("반 구성원 조회 성공",result);
    }

    public ResponseData<List<StudyListRes>> studyList(Club club) {
        Room room = roomService.findByClub(club);
        List<StudyListRes> result = StudyListRes.of(studentService.getStudyMemberList(room));
        return ResponseData.ok("스터디 구성원 조회 성공",result);
    }
}
