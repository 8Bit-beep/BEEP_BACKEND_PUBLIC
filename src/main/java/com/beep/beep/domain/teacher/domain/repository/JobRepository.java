package com.beep.beep.domain.teacher.domain.repository;

import com.beep.beep.domain.teacher.domain.JobEntity;

import com.beep.beep.domain.teacher.presentation.dto.Job;
import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends CrudRepository<JobEntity,String> {
    Job findByUserIdx(Long userIdx);

}
