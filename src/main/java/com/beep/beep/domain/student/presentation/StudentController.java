package com.beep.beep.domain.student.presentation;

import com.beep.beep.domain.room.domain.Club;
import com.beep.beep.domain.student.presentation.dto.request.AttendReq;
import com.beep.beep.domain.student.presentation.dto.request.StudentSignUpReq;
import com.beep.beep.domain.student.presentation.dto.response.AttendListRes;
import com.beep.beep.domain.student.presentation.dto.response.AttendRes;
import com.beep.beep.domain.student.presentation.dto.response.GetStudentOrRoomRes;
import com.beep.beep.domain.student.presentation.dto.response.MemberListRes;
import com.beep.beep.domain.student.presentation.dto.response.StudentCodeRes;
import com.beep.beep.domain.student.presentation.dto.response.StudentInfoRes;
import com.beep.beep.domain.student.presentation.dto.response.StudyListRes;
import com.beep.beep.domain.student.presentation.dto.response.StudyRes;
import com.beep.beep.domain.student.presentation.dto.response.StudyResByFloor;
import com.beep.beep.domain.student.presentation.dto.response.SummarizeStudiesRes;
import com.beep.beep.domain.student.usecase.StudentUseCase;
import com.beep.beep.global.common.dto.response.Response;
import com.beep.beep.global.common.dto.response.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "학생", description = "학생 API")
@RequestMapping("/student")
public class StudentController {

    private final StudentUseCase studentUseCase;

//    @PostMapping("/info")
//    @ResponseStatus(HttpStatus.CREATED)
//    @Operation(summary = "학생 기본정보 저장", description = "회원가입 시 학생 기본 정보를 저장합니다. (unauthenticated)")
//    public Response studentSignUp(
//            @RequestBody StudentSignUpReq req
//    ){
//        return studentUseCase.signUp(req);
//    }

    @GetMapping("/info")
    @Operation(summary = "학생프로필 조회", description = "학생프로필 조회입니다 (student)")
    public ResponseData<StudentInfoRes> studentInfo() {
        return studentUseCase.studentInfo();
    }

    @GetMapping("/code")
    @Operation(summary = "학생 실 코드 조회", description = "학생 실 코드를 조회합니다 (student)")
    public ResponseData<StudentCodeRes> studentCode() {
        return studentUseCase.studentCode();
    }

    @PatchMapping("/attend")
    @Operation(summary = "출석 요청", description = "입실/퇴실을 요청합니다.(student)")
    public ResponseData<AttendRes> attend(
            @RequestBody AttendReq req
    ) {
        return studentUseCase.attend(req);
    }

    @GetMapping("/attend-list")
    @Operation(summary = "출석부 조회", description = "실 코드로 입실한 학생목록 조회합니다. (teacher)")
    public ResponseData<List<AttendListRes>> attendList(
            @RequestParam String code
    ){
        return studentUseCase.attendList(code);
    }

    @GetMapping("/member-list")
    @Operation(summary = "반 구성원 조회", description = "학년-반으로 반 구성원 목록을 조회합니다. (teacher)")
    public ResponseData<List<MemberListRes>> memberList(
            @RequestParam Integer grade,
            @RequestParam Integer cls
    ){
        return studentUseCase.memberList(grade,cls);
    }

    @GetMapping("/study-list")
    @Operation(summary = "스터디 구성원 조회", description = "스터디 출석정보 구성원 조회합니다.(teacher)")
    public ResponseData<List<StudyRes>> studyList(
            @RequestParam Club club
    ){
        return studentUseCase.studyList(club);
    }

    @GetMapping("/{floor}/study-list")
    @Operation(summary = "층별 스터디 구성원 조회", description = "층별 스터디 출석정보 구성원 조회합니다.(teacher)")
    public ResponseData<List<StudyResByFloor>> studyListByFloor(
            @PathVariable("floor") Integer floor
    ){
        return studentUseCase.studyListByFloor(floor);
    }

    @GetMapping("/{floor}/study")
    @Operation(summary = "스터디 미리보기 리스트", description = "스터디 미리보기 리스트를 조회합니다.(teacher)")
    public ResponseData<List<SummarizeStudiesRes>> summarizeStudies(
            @PathVariable("floor") Integer floor
    ){
        return studentUseCase.summarizeStudies(floor);
    }

    @GetMapping("")
    @Operation(summary = "학생과 실 조회", description = "검색어를 받아 학생과 실을 검색합니다.(teacher)")
    public ResponseData<GetStudentOrRoomRes> getStudentOrRoom(
            @RequestParam String keyword
    ){
        return studentUseCase.getStudentOrRoom(keyword);
    }

}
