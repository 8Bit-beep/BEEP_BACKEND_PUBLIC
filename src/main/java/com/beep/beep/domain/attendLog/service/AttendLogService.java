package com.beep.beep.domain.attendLog.service;

import com.beep.beep.domain.attendLog.domain.AttendLog;
import com.beep.beep.domain.attendLog.domain.enums.TimeTable;
import com.beep.beep.domain.attendLog.domain.repo.AttendLogJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendLogService {

    private final AttendLogJpaRepo attendLogJpaRepo;

    public List<AttendLog> getAttendLogList(String year, String month, String date, TimeTable timeTable) {
        return attendLogJpaRepo.findAllByCurrentDt(year,month,date,timeTable);
    }
}
