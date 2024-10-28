package com.beep.beep.domain.attendLog.presentation.dto.request;

import com.beep.beep.domain.attendLog.domain.enums.TimeTable;

/**
 * 특정 날짜의 교시 출석로그 조회 요청 dto
 * */
public record AttendLogsReq(
        String year,
        String month,
        String date,
        TimeTable timeTable) {
}
