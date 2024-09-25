package com.beep.beep.domain.attendLog.domain.repo;

import com.beep.beep.domain.attendLog.domain.AttendLog;
import com.beep.beep.domain.attendLog.domain.enums.TimeTable;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AttendLogJpaRepo extends JpaRepository<AttendLog, Long> {
    @Modifying
    @Query(value = "INSERT INTO tb_attend_log (email,current_room,last_updated,current_dt,time_table)"+
            "SELECT u.email,u.current_room,u.last_updated,NOW(),:timeTable"+
            "FROM tb_user u WHERE u.authority = 'STUDENT'",nativeQuery = true)
    void saveAllAttendLog(@Param("timeTable") TimeTable timeTable);
}
