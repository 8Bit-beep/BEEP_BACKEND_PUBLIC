package com.beep.beep.domain.teacher.domain.facade;


import com.beep.beep.domain.teacher.domain.Job;
import com.beep.beep.domain.teacher.domain.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherFacade {

    private final JobRepository jobRepository;

    public Job findByUserIdx(Long userIdx){
        return jobRepository.findByUserIdx(userIdx);
    }

    public void save(Job job){
        jobRepository.save(job);
    }

}
