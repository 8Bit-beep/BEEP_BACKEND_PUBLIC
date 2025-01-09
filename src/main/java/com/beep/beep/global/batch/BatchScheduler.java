package com.beep.beep.global.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BatchScheduler {

    private final JobLauncher jobLauncher;
    private final Job createRepeatSchedule;

    // 오후 8시 45분 이후 초기화
    @Scheduled(cron = "0 0 22 * * ?")
    public void runCreateRepeatScheduleAt10PM() {
        runJob("22:00 오후 10시",true); // 초기화
    }

    // 오전 9시 이후 초기화
    @Scheduled(cron = "0 0 13 * * ?")
    public void runCreateRepeatScheduleAt1PM() {
        runJob("13:00 오후 1시",true); // 초기화
    }

    // 오후 1시 20분 이후 초기화
    @Scheduled(cron = "0 50 15 * * ?")
    public void runCreateRepeatScheduleAt350PM() {
        runJob("15:50 오후 1시",true); // 초기화
    }

    // 오후 4시 이후 초기화
    @Scheduled(cron = "0 40 20 * * ?")
    public void runCreateRepeatScheduleAt840PM() {
        runJob("20:40 오후 8시 40분",true); // 초기화
    }

//    // 오후 5시 25분 (17:25)
//    @Scheduled(cron = "0 25 17 * * ?")
//    public void runCreateRepeatScheduleAt520PM() {
//        runJob("17:25 오후 5시 25분",false);
//    }

//    // 오후 6시 20분 (18:20)
//    @Scheduled(cron = "0 20 18 * * ?")
//    public void runCreateRepeatScheduleAt620PM() {
//        runJob("18:15 오후 6시 20분",true); // 초기화
//    }
//
//    // 오후 8시 (20:00)
//    @Scheduled(cron = "0 0 20 * * ?")
//    public void runCreateRepeatScheduleAt7PM() {
//        runJob("20:00 오후 8시",false);
//    }
//
//    // 오후 8시 45분 (20:45)
//    @Scheduled(cron = "0 45 20 * * ?")
//    public void runCreateRepeatScheduleAt845PM() {
//        runJob("20:45 오후 8시 45분",true); // 초기화
//    }
//
//    // 오후 9시 30분 (21:30)
//    @Scheduled(cron = "0 30 21 * * ?")
//    public void runCreateRepeatScheduleAt930PM() {
//        runJob("20:45 오후 8시 45분",true); // 초기화
//    }

    /**
     * RepeatType에 따른 반복 일정 생성
     * @param scheduleTime 실행 시간 정보
     */
    public void runJob(String scheduleTime, boolean init) {
        log.info("===========================================");
        log.info("{}에 각 학생 출석코드 초기화 배치를 시작합니다.", scheduleTime);
        log.info("===========================================");

        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .addString("init", String.valueOf(init))
                .toJobParameters();

        try {
            jobLauncher.run(createRepeatSchedule, jobParameters);
            log.info("{}에 출석코드 초기화 배치가 성공적으로 완료되었습니다.", scheduleTime);
        } catch (Exception e) {
            log.error("{}에 출석코드 초기화 배치가 실패하였습니다. : ", scheduleTime, e);
        }
    }
}

