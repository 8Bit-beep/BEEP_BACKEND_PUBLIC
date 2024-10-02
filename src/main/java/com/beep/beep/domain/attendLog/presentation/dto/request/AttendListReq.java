package com.beep.beep.domain.attendLog.presentation.dto.request;

import com.beep.beep.domain.attendLog.domain.enums.TimeTable;

public record AttendListReq(int year, int month, int date, TimeTable timeTable) {
}
