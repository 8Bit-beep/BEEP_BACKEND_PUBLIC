package com.beep.beep.domain.beep.domain.repository;

import com.beep.beep.domain.beep.domain.Attendance;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AttendanceRepository extends CrudRepository<Attendance,String> {

    Attendance findByUserIdx(Long userIdx);

    List<Attendance> findAllByCode(String code);
}
