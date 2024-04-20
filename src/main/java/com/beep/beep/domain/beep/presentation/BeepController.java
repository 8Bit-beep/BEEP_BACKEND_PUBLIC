package com.beep.beep.domain.beep.presentation;

import com.beep.beep.domain.beep.service.BeepService;
import com.beep.beep.domain.student.presentation.dto.request.EnterRoomRequest;
import com.beep.beep.domain.student.presentation.dto.request.ExitRoomRequest;
import com.beep.beep.domain.student.presentation.dto.response.GetAttendanceResponse;
import com.beep.beep.domain.student.presentation.dto.response.GetRoomResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/beep")
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

    @GetMapping("/rooms/{name}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Searching Rooms API")
    public List<GetRoomResponse> getRooms(
            @PathVariable String name
    ){
        return beepService.getRooms(name);
    }

    @GetMapping("/attendance/{code}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get Attendance of Room API")
    public List<GetAttendanceResponse> getAttendance(
            @PathVariable String code
    ){
        return beepService.getAttendance(code);
    }
}
