package com.beep.beep.domain.attendLog.domain.repo;

import com.beep.beep.domain.attendLog.domain.AttendLog;
import com.beep.beep.domain.attendLog.domain.enums.TimeTable;
import com.beep.beep.domain.student.presentation.dto.response.TodayLastLogs;
import com.beep.beep.domain.user.domain.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

public interface AttendLogJpaRepo extends JpaRepository<AttendLog, Long> {


    /**
     * 출석 로그 저장
     * - 모든 유저(student권한) 테이블에 저장된 current_room을 기록
     * */
    @Modifying
    @Query(value = "INSERT INTO tb_attend_log (fk_user_id,current_room,last_updated,current_dt,time_table) "+
            "SELECT u.email,u.current_room,u.last_updated,:current_dt,:timeTable "+
            "FROM tb_user u WHERE u.authority = 'STUDENT'",nativeQuery = true)
    @Transactional(rollbackFor = Exception.class)
    void saveAllAttendLog(@Param("current_dt") LocalDateTime current_dt, @Param("timeTable") String timeTable);

    /**
     * 출석로그 조회
     * - 년,월,일, 교시 로 그날의 출석로그 조회
     * */
    @Query("select a from AttendLog a "+
            "join a.user u " +
            "where year(a.currentDt) = :year and " +
            "month(a.currentDt) = :month and " +
            "day(a.currentDt) = :date and " +
            "a.timeTable = :timeTable")
    List<AttendLog> findAllByCurrentDt(@Param("year") String year,@Param("month") String month,@Param("date") String date,@Param("timeTable") TimeTable timeTable);

    /**
     * 오늘자 저장된 출석로그 조회
     * @return List<TodayLastLogs> (dto를 바로 반환)
     * */
    @Query("SELECT new com.beep.beep.domain.student.presentation.dto.response.TodayLastLogs(a.timeTable,a.lastUpdated,a.currentRoom) " +
            "FROM AttendLog a " +
            "JOIN a.user u " +
            "WHERE DATE(a.currentDt) = :now AND " +
            "a.user = :user")
    List<TodayLastLogs> findAllByCurrentDtAndUser(@Param("user") User user,@Param("now") LocalDate now);

    /**
     * 월별 출석로그 조회
     * */
    @Query("select a from AttendLog a "+
            "join fetch a.user u " +
            "where year(a.currentDt) = :year and " +
            "month(a.currentDt) = :month")
    List<AttendLog> findAllByMonth(@Param("year") String year, @Param("month") String month);
}
