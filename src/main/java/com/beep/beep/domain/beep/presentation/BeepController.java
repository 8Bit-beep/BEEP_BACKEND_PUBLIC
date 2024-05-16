package com.beep.beep.domain.beep.presentation;

import com.beep.beep.domain.beep.service.BeepService;
import com.beep.beep.domain.beep.presentation.dto.request.EnterRoomRequest;
import com.beep.beep.domain.beep.presentation.dto.request.ExitRoomRequest;
import com.beep.beep.domain.beep.presentation.dto.response.GetAttendanceResponse;
import com.beep.beep.domain.beep.presentation.dto.response.GetRoomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
    @Operation(summary = "입실 요청", description = "입실을 요청합니다.(student)")
    public void saveAttendance() {
        beepService.saveAttendance();
    }

    @PutMapping("/enter")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "입실 요청", description = "입실을 요청합니다.(student)")
    public void enterRoom(
            @RequestBody EnterRoomRequest request
    ) {
        beepService.enter(request);
    }

    @PutMapping("/exit")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "퇴실 요청", description = "퇴실을 요청합니다.(student)")
    public void exitRoom(
            @RequestBody ExitRoomRequest request
    ) {
        beepService.exit(request);
    }

    @GetMapping("/rooms")
    @Operation(summary = "실 조회", description = "실 이름으로 실을 조회합니다.(teacher)")
    public List<GetRoomResponse> getRooms(
            @RequestParam String name
    ){
        return beepService.getRooms(name);
    }

    @GetMapping("/attendances")
    @Operation(summary = "출석 조회", description = "실 코드로 입실한 학생목록 조회합니다. (teacher)")
    public List<GetAttendanceResponse> getAttendance(
            @RequestParam String code
    ){
        return beepService.getAttendance(code);
    }
}
