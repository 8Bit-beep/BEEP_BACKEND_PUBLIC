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
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {

    private final RepeatScheduleTasklet repeatScheduleTasklet;
    private final SendNotificationTasklet sendNotificationTasklet;
    private final SendFailNotificationTasklet sendFailNotificationTasklet;

    @Bean
    public Job createRepeatSchedule(JobRepository jobRepository, Step createScheduleStep) {
        return new JobBuilder("createRepeatSchedule", jobRepository)
                .start(createScheduleStep)
                .build();
    }

    @Bean
    public Step createScheduleStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("createScheduleStep", jobRepository)
                .tasklet(repeatScheduleTasklet, transactionManager)
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
    public Step sendNotificationStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("sendNotificationStep", jobRepository)
                .tasklet(sendNotificationTasklet, transactionManager)
                .build();
    }

    @Bean
    public Step sendFailNotificationStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("sendFailNotificationStep", jobRepository)
                .tasklet(sendFailNotificationTasklet, transactionManager)
                .build();
    }

}
