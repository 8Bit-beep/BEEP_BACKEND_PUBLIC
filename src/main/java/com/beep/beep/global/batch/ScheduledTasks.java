package com.beep.beep.global.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduledTasks {

    private final JobLauncher jobLauncher;

    private final Job createRepeatSchedule;

    /**
     * RepeatType에 따른 반복 일정 생성
     * @cron 매일 밤 자정에 생성
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void runCreateRepeatSchedule() {
        log.info("===========================================");
        log.info("각 학생 출석코드 초기화 배치를 시작합니다.");
        log.info("===========================================");
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        try {
            jobLauncher.run(createRepeatSchedule, jobParameters);
        } catch (Exception e) {
            log.error("각 학생 출석코드 초기화 배치를 실패하였습니다. : ", e);
        }
    }
}
