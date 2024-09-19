package com.beep.beep.global.batch;

import com.beep.beep.domain.student.domain.repository.StudentJpaRepo;
import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RepeatScheduleTasklet implements Tasklet {

    private final StudentJpaRepo studentJpaRepo;

    @Override
    @Transactional
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("Executing RepeatScheduleTasklet...");
        studentJpaRepo.updateAllRoomToNull(); // Repository 메서드 호출
        log.info("All student codes have been set to null");
        return RepeatStatus.FINISHED;
    }
}
