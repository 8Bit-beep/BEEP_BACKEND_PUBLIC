package com.beep.beep.domain.attendLog.presentation;

import com.beep.beep.domain.attendLog.presentation.dto.request.AttendLogsReq;
import com.beep.beep.domain.attendLog.presentation.dto.request.MonthlyAttendLogsReq;
import com.beep.beep.domain.attendLog.presentation.dto.response.AttendLogRes;
import com.beep.beep.domain.attendLog.presentation.dto.response.MonthlyAttendLogsRes;
import com.beep.beep.domain.attendLog.usecase.AttendLogUseCase;
import com.beep.beep.global.common.dto.response.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/attend-log")
@RequiredArgsConstructor
public class AttendLogController {

    private final AttendLogUseCase attendLogUseCase;

    @GetMapping("")
    @Operation(summary = "출석 기록 조회", description = "춣석 기록을 조회합니다. (teacher)")
    public ResponseData<List<AttendLogRes>> attendLogList(
            @ModelAttribute AttendLogsReq req
    ) {
        return attendLogUseCase.getAttendLogs(req);
    }

    @GetMapping("/monthly")
    @Operation(summary = "월별 출석 기록 조회", description = "월별 춣석 기록을 조회합니다. (teacher)")
    public ResponseData<List<MonthlyAttendLogsRes>> monthlyAttendLogList(
            @ModelAttribute MonthlyAttendLogsReq req
    ) {
        return attendLogUseCase.getMonthlyAttendLogs(req);
    }
}
