package com.beep.beep.domain.attendLog.domain.repo;

import com.beep.beep.domain.attendLog.domain.AttendLog;
import com.beep.beep.domain.attendLog.domain.enums.TimeTable;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

public interface AttendLogJpaRepo extends JpaRepository<AttendLog, Long> {
    @Modifying
    @Query(value = "INSERT INTO tb_attend_log (fk_user_id,current_room,last_updated,current_dt,time_table) "+
            "SELECT u.email,u.current_room,u.last_updated,:current_dt,:timeTable "+
            "FROM tb_user u WHERE u.authority = 'STUDENT'",nativeQuery = true)
    @Transactional(rollbackFor = Exception.class)
    void saveAllAttendLog(@Param("current_dt") LocalDateTime current_dt, @Param("timeTable") String timeTable);

    @Query("select a from AttendLog a "+
            "join a.user u " +
            "where year(a.currentDt) = :year and " +
            "month(a.currentDt) = :month and " +
            "day(a.currentDt) = :date and " +
            "a.timeTable = :timeTable")
    List<AttendLog> findAllByCurrentDt(@Param("year") String year,@Param("month") String month,@Param("date") String date,@Param("timeTable") TimeTable timeTable);

}
