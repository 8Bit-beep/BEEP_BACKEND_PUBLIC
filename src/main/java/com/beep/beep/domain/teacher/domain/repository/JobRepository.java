package com.beep.beep.domain.teacher.domain.repository;

import com.beep.beep.domain.teacher.domain.JobEntity;

import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends CrudRepository<JobEntity,String> {
    JobEntity findByUserIdx(Long userIdx);

}
