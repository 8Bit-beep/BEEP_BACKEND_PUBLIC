package com.beep.beep.domain.student.presentation;


import com.beep.beep.domain.student.presentation.dto.response.StudentInfoResponse;
import com.beep.beep.domain.student.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/info")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get StudentInfo API")
    public StudentInfoResponse getInfo(
            @RequestHeader(name = "Authorization") String token
    ) {
        return studentService.getStudentInfo(token);
    }



}
