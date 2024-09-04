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

    @Scheduled(cron = "0 0 0 * * ?") // 자정 (00:00)
    public void runCreateRepeatScheduleAtMidnight() {
        initializeCode();
    }

    @Scheduled(cron = "0 0 19 * * ?") // 오후 7시 (19:00)
    public void runCreateRepeatScheduleAt7PM() {
        initializeCode();
    }

    @Scheduled(cron = "0 45 20 * * ?") // 오후 8시 45분 (20:45)
    public void runCreateRepeatScheduleAt845PM() {
        initializeCode();
    }

    /**
     * RepeatType에 따른 반복 일정 생성
     * @cron 매일 밤 자정에 생성
     */
    public void initializeCode() {
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
