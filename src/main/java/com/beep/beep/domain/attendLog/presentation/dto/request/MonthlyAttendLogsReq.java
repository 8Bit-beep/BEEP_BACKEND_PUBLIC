package com.beep.beep.domain.attendLog.presentation.dto.request;

/**
 * 월별 출석로그 조회요청 dto
 * */
public record MonthlyAttendLogsReq(
        String year,
        String month
) {
}
