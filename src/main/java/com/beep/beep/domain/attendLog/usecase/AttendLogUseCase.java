package com.beep.beep.domain.attendLog.usecase;

import com.beep.beep.domain.attendLog.presentation.dto.request.AttendListReq;
import com.beep.beep.domain.attendLog.presentation.dto.request.MonthlyAttendLogsReq;
import com.beep.beep.domain.attendLog.presentation.dto.response.AttendLogRes;
import com.beep.beep.domain.attendLog.presentation.dto.response.MonthlyAttendLogsRes;
import com.beep.beep.domain.attendLog.service.AttendLogService;
import com.beep.beep.global.common.dto.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AttendLogUseCase {

    private final AttendLogService attendLogService;

    public ResponseData<List<AttendLogRes>> getAttendLogs(AttendListReq req) {
        List<AttendLogRes> result = AttendLogRes.of(attendLogService.getAttendLogList(req.year(),req.month(),req.date(),req.timeTable()));
        return ResponseData.ok("출석 기록 조회 성공",result);
    }

    public ResponseData<List<MonthlyAttendLogsRes>> getMonthlyAttendLogs(MonthlyAttendLogsReq req) {
        List<MonthlyAttendLogsRes> result = MonthlyAttendLogsRes.of(attendLogService.getMonthlyAttendLogs(req.year(),req.month()));
        return ResponseData.ok("출석 기록 조회 성공",result);
    }
}
