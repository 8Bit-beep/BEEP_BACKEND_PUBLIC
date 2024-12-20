package com.beep.beep.global.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableBatchProcessing
public class BatchConfig {

    private final InitScheduleTasklet initScheduleTasklet;

    @Bean
    public Job createRepeatSchedule(JobRepository jobRepository, Step createScheduleStep) {
        return new JobBuilder("createRepeatSchedule", jobRepository)
                .start(createScheduleStep)
                .build();
    }

    @Bean
    public Step createScheduleStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("createScheduleStep", jobRepository)
                .tasklet(initScheduleTasklet, transactionManager)
                .build();
    }

    @Bean
    public Job sendNotification(JobRepository jobRepository, Step sendNotificationStep, Step sendFailNotificationStep) {
        return new JobBuilder("sendNotificationSchedule", jobRepository)
                .start(sendNotificationStep)
                .next(sendFailNotificationStep)
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }

}
