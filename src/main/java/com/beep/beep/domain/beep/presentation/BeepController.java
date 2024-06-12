package com.beep.beep.domain.beep.presentation;

import com.beep.beep.domain.beep.presentation.dto.request.InitializeAttendanceReq;
import com.beep.beep.domain.beep.service.BeepService;
import com.beep.beep.domain.beep.presentation.dto.request.EnterRoomReq;
import com.beep.beep.domain.beep.presentation.dto.request.ExitRoomReq;
import com.beep.beep.domain.beep.presentation.dto.response.AttendanceByCodeRes;
import com.beep.beep.domain.beep.presentation.dto.RoomVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/beep")
@Tag(name = "BEEP", description = "출석,출석부 조회,실 조회 API")
public class BeepController {

    private final BeepService beepService;

    @PostMapping("/attendances")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "출석정보 초기화", description = "출석정보 초기값을 설정합니다.(student)")
    public void initializeAttendance(
            @RequestBody InitializeAttendanceReq request
    ) {
        beepService.initializeAttendance(request);
    }

    @PutMapping("/enter")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "입실 요청", description = "입실을 요청합니다.(student)")
    public void enterRoom(
            @RequestBody EnterRoomReq request
    ) {
        beepService.enter(request);
    }

    @PutMapping("/exit")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "퇴실 요청", description = "퇴실을 요청합니다.(student)")
    public void exitRoom(
            @RequestBody ExitRoomReq request
    ) {
        beepService.exit(request);
    }

    @GetMapping("/rooms/name")
    @Operation(summary = "실 조회", description = "실 이름으로 실을 조회합니다.(teacher)")
    public List<RoomVO> roomListByName(
            @RequestParam String name
    ){
        return beepService.roomListByName(name);
    }

    @GetMapping("/rooms/floor")
    @Operation(summary = "실 조회", description = "n층으로 실을 조회합니다.(teacher)")
    public List<RoomVO> roomListByFloor(
            @RequestParam Integer floor
    ){
        return beepService.roomListByFloor(floor);
    }

    @GetMapping("/attendances")
    @Operation(summary = "출석 조회", description = "실 코드로 입실한 학생목록 조회합니다. (teacher)")
    public List<AttendanceByCodeRes> attendanceByCode(
            @RequestParam String code
    ){
        return beepService.attendanceByCode(code);
    }

}
