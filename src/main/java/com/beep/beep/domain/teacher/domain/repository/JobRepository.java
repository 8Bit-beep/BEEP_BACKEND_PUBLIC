package com.beep.beep.domain.teacher.domain.repository;

import com.beep.beep.domain.teacher.domain.JobEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobRepository extends JpaRepository<JobEntity,Long> {
    Optional<JobEntity> findByUserIdx(Long userIdx);

}
