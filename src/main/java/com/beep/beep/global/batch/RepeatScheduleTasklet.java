package com.beep.beep.global.batch;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RepeatScheduleTasklet implements Tasklet {

    private final EntityManagerFactory entityManagerFactory;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            // Student 테이블의 모든 학생에 대해 code 컬럼을 null로 설정
            entityManager.createQuery("UPDATE Student s SET s.code = \"\"").executeUpdate();

            entityManager.getTransaction().commit();
            log.info("All student codes have been set to null");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            log.error("Error during setting student codes to null", e);
            throw e;
        } finally {
            entityManager.close();
        }
        return RepeatStatus.FINISHED;
    }
}
