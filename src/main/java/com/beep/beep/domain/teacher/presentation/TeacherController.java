package com.beep.beep.domain.teacher.presentation;


import com.beep.beep.domain.student.presentation.dto.response.GetAttendanceResponse;
import com.beep.beep.domain.student.presentation.dto.response.GetRoomResponse;
import com.beep.beep.domain.student.presentation.dto.response.GetStudentResponse;
import com.beep.beep.domain.student.presentation.dto.response.SearchStudentResponse;
import com.beep.beep.domain.teacher.presentation.dto.response.TeacherInfoResponse;
import com.beep.beep.domain.teacher.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/teacher")
@RequiredArgsConstructor
@Tag(name = "Get Info Server (Teacher)")
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get TeacherInfo API")
    public TeacherInfoResponse getInfo(
            @RequestHeader("Authorization") String token
    ) {
        return teacherService.getTeacherInfo(token);
    }

    @GetMapping("/rooms/{name}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Searching Rooms API")
    public List<GetRoomResponse> getRooms(
            @PathVariable String name
    ){
        return teacherService.getRooms(name);
    }

    @GetMapping("/attendance/{code}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get Attendance of Room API")
    public List<GetAttendanceResponse> getAttendance(
            @PathVariable String code
    ){
        return teacherService.getAttendance(code);
    }

    @GetMapping("/students/{grade}/{cls}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get Students' Info By Grade-Cls API")
    public List<GetStudentResponse> getStudents(
            @PathVariable int grade,
            @PathVariable int cls
    ){
        return teacherService.getStudents(grade,cls);
    }

    @GetMapping("/students")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Search Students API")
    public List<SearchStudentResponse> searchStudents(
            @RequestParam String name
    ){
        return teacherService.searchStudents(name);
    }

}
