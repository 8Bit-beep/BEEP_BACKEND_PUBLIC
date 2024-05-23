package com.beep.beep.domain.beep.domain.repository;

import com.beep.beep.domain.beep.domain.Attendance;
import com.beep.beep.domain.beep.domain.repository.querydsl.AttendanceRepositoryCustom;
import org.springframework.data.repository.CrudRepository;

public interface AttendanceRepository extends CrudRepository<Attendance,Long> , AttendanceRepositoryCustom {
}
