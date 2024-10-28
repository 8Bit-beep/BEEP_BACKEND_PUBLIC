package com.beep.beep.domain.attendLog.service;

import com.beep.beep.domain.attendLog.domain.AttendLog;
import com.beep.beep.domain.attendLog.domain.enums.TimeTable;
import com.beep.beep.domain.attendLog.domain.repo.AttendLogJpaRepo;
import com.beep.beep.domain.student.presentation.dto.response.TodayLastLogs;
import com.beep.beep.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AttendLogService {

    private final AttendLogJpaRepo attendLogJpaRepo;

    public List<AttendLog> getAttendLogList(String year, String month, String date, TimeTable timeTable) {
        return attendLogJpaRepo.findAllByCurrentDt(year,month,date,timeTable);
    }

    /**
     * 오늘 저장된 출석로그 리스트 조회
     * */
    public List<TodayLastLogs> getTodayLog(User user) {
        return attendLogJpaRepo.findAllByCurrentDtAndUser(user, LocalDate.now(ZoneId.of("Asia/Seoul")));
    }

    public List<AttendLog> getMonthlyAttendLogs(String year, String month) {
        return attendLogJpaRepo.findAllByMonth(year,month);
    }
}
