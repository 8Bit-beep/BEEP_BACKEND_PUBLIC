package com.beep.beep.domain.student.presentation;


import com.beep.beep.domain.student.presentation.dto.request.EnterRoomRequest;
import com.beep.beep.domain.student.presentation.dto.request.ExitRoomRequest;
import com.beep.beep.domain.student.presentation.dto.response.StudentInfoResponse;
import com.beep.beep.domain.student.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/student")
@RequiredArgsConstructor
@Tag(name = "Get Info Server (Student)")
public class StudentController {

    private final StudentService studentService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get StudentInfo API")
    public StudentInfoResponse getInfo(
            @RequestHeader(name = "Authorization") String token
    ) {
        return studentService.getStudentInfo(token);
    }

    @PutMapping("/enter")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Entering Room API")
    public void enterRoom(
            @RequestHeader(name = "Authorization") String token,
            @RequestBody EnterRoomRequest request
    ) {
        studentService.enter(token,request);
    }

    @PutMapping("/exit")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Exiting Room API")
    public void exitRoom(
            @RequestHeader(name = "Authorization") String token,
            @RequestBody ExitRoomRequest request
    ) {
        studentService.exit(token,request);
    }

}
