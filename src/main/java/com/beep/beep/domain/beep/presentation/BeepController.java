package com.beep.beep.domain.beep.presentation;


import com.beep.beep.domain.beep.presentation.dto.request.EnterRoomRequest;
import com.beep.beep.domain.beep.presentation.dto.request.ExitRoomRequest;
import com.beep.beep.domain.beep.presentation.dto.response.GetAttendanceResponse;
import com.beep.beep.domain.beep.presentation.dto.response.GetRoomResponse;
import com.beep.beep.domain.beep.presentation.dto.response.GetStudentResponse;
import com.beep.beep.domain.beep.presentation.dto.response.SearchStudentResponse;
import com.beep.beep.domain.beep.service.BeepService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/beep")
@RequiredArgsConstructor
@Tag(name = "Beep Server (Student,Teacher)")
public class BeepController {

    private final BeepService beepService;

    @PutMapping("/enter")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Entering Room API")
    public void enterRoom(
            @RequestHeader(name = "Authorization") String token,
            @RequestBody EnterRoomRequest request
    ) {
        beepService.enter(token,request);
    }

    @PutMapping("/exit")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Exiting Room API")
    public void exitRoom(
            @RequestHeader(name = "Authorization") String token,
            @RequestBody ExitRoomRequest request
    ) {
        beepService.exit(token,request);
    }

    @GetMapping("/rooms")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Searching Rooms API")
    public List<GetRoomResponse> getRooms(
            @RequestParam String name
    ){
        return beepService.getRooms(name);
    }

    @GetMapping("/attendance")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get Attendance of Room API")
    public List<GetAttendanceResponse> getAttendance(
            @RequestParam String code
    ){
        return beepService.getAttendance(code);
    }

    @GetMapping("/students")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get Students' Info By Grade-Cls API")
    public List<GetStudentResponse> getStudents(
            @RequestParam int grade,
            @RequestParam int cls
    ){
        return beepService.getStudents(grade,cls);
    }

    @GetMapping("/{name}/students")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Search Students API")
    public List<SearchStudentResponse> searchStudents(
            @PathVariable String name
    ){
        return beepService.searchStudents(name);
    }



}
