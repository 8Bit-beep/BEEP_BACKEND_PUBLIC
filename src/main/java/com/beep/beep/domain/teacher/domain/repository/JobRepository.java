package com.beep.beep.domain.teacher.domain.repository;

import com.beep.beep.domain.teacher.domain.Job;

import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends CrudRepository<Job,String> {
    Job findByUserIdx(Long userIdx);

}
