package com.beep.beep.domain.beep.domain.repository;

import com.beep.beep.domain.beep.domain.AttendanceEntity;
import com.beep.beep.domain.beep.presentation.dto.Attendance;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AttendanceRepository extends CrudRepository<AttendanceEntity,String> {

    Attendance findByUserIdx(Long userIdx);

    List<Attendance> findAllByCode(String code);
}
