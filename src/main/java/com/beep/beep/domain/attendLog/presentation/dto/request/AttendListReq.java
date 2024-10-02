package com.beep.beep.domain.attendLog.presentation.dto.request;

import com.beep.beep.domain.attendLog.domain.enums.TimeTable;

public record AttendListReq(String year, String month, String date, TimeTable timeTable) {
}
