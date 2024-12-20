package com.beep.beep.domain.schedule.service;

import com.beep.beep.domain.attendLog.domain.enums.TimeTable;
import com.beep.beep.domain.schedule.domain.Schedule;
import com.beep.beep.domain.schedule.domain.repo.ScheduleJpaRepo;
import com.beep.beep.domain.schedule.presentation.dto.response.ScheduleRes;
import com.beep.beep.domain.schedule.usecase.ScheduleUseCase;
import com.beep.beep.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ScheduleService {

    private final ScheduleJpaRepo scheduleJpaRepo;

    public void register(Schedule schedule) {
        scheduleJpaRepo.save(schedule);
    }

    public List<Schedule> getTodaySchedules(DayOfWeek dayOfWeek) {
        return scheduleJpaRepo.findAllByDayOfWeek(dayOfWeek);
    }

    public ScheduleRes getCurrentSchedule(User user, TimeTable timeTable) {
        return scheduleJpaRepo.findByUserAndTimeTable(user,timeTable);
    }

    public void deleteAll() {
        scheduleJpaRepo.deleteAll();
    }

    public List<Schedule> findAll() {
        return scheduleJpaRepo.findAll();
    }
}
