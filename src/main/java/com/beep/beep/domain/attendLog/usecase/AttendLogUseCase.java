package com.beep.beep.domain.attendLog.usecase;

import com.beep.beep.domain.attendLog.presentation.dto.request.AttendListReq;
import com.beep.beep.domain.attendLog.presentation.dto.response.AttendLogRes;
import com.beep.beep.domain.attendLog.service.AttendLogService;
import com.beep.beep.global.common.dto.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AttendLogUseCase {

    private final AttendLogService attendLogService;


    public ResponseData<List<AttendLogRes>> attendLogList(AttendListReq req) {
        List<AttendLogRes> result = AttendLogRes.of(attendLogService.getAttendLogList(req.year(),req.month(),req.date(),req.timeTable()));
        return ResponseData.ok("출석 기록 조회 성공",result);
    }
}
