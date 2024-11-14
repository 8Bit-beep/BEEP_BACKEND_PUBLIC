package com.beep.beep.domain.schedule.domain.repo;

import com.beep.beep.domain.attendLog.domain.enums.TimeTable;
import com.beep.beep.domain.schedule.domain.Schedule;
import com.beep.beep.domain.schedule.presentation.dto.response.ScheduleRes;
import com.beep.beep.domain.user.domain.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.DayOfWeek;
import java.util.List;

public interface ScheduleJpaRepo extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByDayOfWeek(DayOfWeek dayOfWeek);

    @Query("SELECT new com.beep.beep.domain.schedule.presentation.dto.response.ScheduleRes(s.cause,s.room) " +
            "FROM Schedule s " +
            "JOIN s.user u " +
            "WHERE s.user = :user AND " +
            "s.timeTable = :timeTable")
    ScheduleRes findByUserAndTimeTable(@Param("user") User user,@Param("timeTable") TimeTable timeTable);

}
