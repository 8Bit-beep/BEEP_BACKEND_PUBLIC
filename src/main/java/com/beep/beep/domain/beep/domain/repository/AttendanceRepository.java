package com.beep.beep.domain.beep.domain.repository;

import com.beep.beep.domain.beep.domain.AttendanceEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AttendanceRepository extends CrudRepository<AttendanceEntity,String> {

    AttendanceEntity findByUserIdx(Long userIdx);

    List<AttendanceEntity> findAllByCode(String code);
}
