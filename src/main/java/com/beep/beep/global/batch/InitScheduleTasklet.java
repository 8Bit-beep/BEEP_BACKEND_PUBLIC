package com.beep.beep.global.batch;

import com.beep.beep.domain.attendLog.domain.enums.TimeTable;
import com.beep.beep.domain.attendLog.domain.repo.AttendLogJpaRepo;
import com.beep.beep.domain.user.domain.repo.UserJpaRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.beep.beep.domain.user.domain.enums.RoomCode.NOTFOUND;

@Slf4j
@Component
@RequiredArgsConstructor
public class InitScheduleTasklet implements Tasklet {

    private final UserJpaRepo userJpaRepo;
    private final AttendLogJpaRepo attendLogJpaRepo;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        // JobParameters에서 init 값 가져오기
        boolean init = Boolean.parseBoolean(chunkContext.getStepContext()
                .getJobParameters()
                .get("init")
                .toString());

        // 1. 로그 저장
        log.info("Executing InitScheduleTasklet...");
        attendLogJpaRepo.saveAllAttendLog(TimeTable.of().getValue());
        log.info("All student's attend-log saved");

        // 2. 유저 code 초기화
        if(init) {
            userJpaRepo.updateAllRoomToNotFound(NOTFOUND); // Repository 메서드 호출
        }
        log.info("All student codes have been set to NOTFOUND");
        return RepeatStatus.FINISHED;
    }


}
