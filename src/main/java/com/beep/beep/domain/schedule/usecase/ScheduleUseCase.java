package com.beep.beep.domain.schedule.usecase;

import com.beep.beep.domain.attendLog.domain.enums.TimeTable;
import com.beep.beep.domain.schedule.domain.Schedule;
import com.beep.beep.domain.schedule.exception.ScheduleRegisterException;
import com.beep.beep.domain.schedule.presentation.dto.response.GetTodaySchedulesRes;
import com.beep.beep.domain.schedule.service.ScheduleService;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.service.UserService;
import com.beep.beep.global.common.dto.response.Response;
import com.beep.beep.global.common.dto.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.DayOfWeek;
import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ScheduleUseCase {

    private final ScheduleService scheduleService;
    private final UserService userService;

    public Response register(MultipartFile file){
        // 1. 학번으로 학생 조회해서 저장
        try{
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet worksheet = workbook.getSheetAt(0);

            for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {

                DataFormatter formatter = new DataFormatter();
                XSSFRow row = worksheet.getRow(i); // 몇번째 행인지

                // user 찾기
                Integer grade = Integer.valueOf(formatter.formatCellValue(row.getCell(0))); // 0번째 열 : 학년
                Integer cls = Integer.valueOf(formatter.formatCellValue(row.getCell(1))); // 1번째 열 : 반
                Integer num = Integer.valueOf(formatter.formatCellValue(row.getCell(2))); // 2번째 열 : 번호
                String name = formatter.formatCellValue(row.getCell(3)); // 3번째 열 : 이름

                User user = userService.findByStudentIdAndName(grade,cls,num,name);

                // 일정 추출
                String cause = formatter.formatCellValue(row.getCell(4)); // 4번째 열 : 이유 -> 방과후명 혹은 나르샤명 혹은 특강 등
                String room = formatter.formatCellValue(row.getCell(5)); // 5번째 열 : 실
                DayOfWeek day = DayOfWeek.valueOf(formatter.formatCellValue(row.getCell(6)));  // 6번째 열 : 요일
                TimeTable timeTable = TimeTable.valueOf(formatter.formatCellValue(row.getCell(7))); // 7번째 열 : 교시

                scheduleService.register(Schedule.builder()
                        .cause(cause)
                        .room(room)
                        .dayOfWeek(day)
                        .timeTable(timeTable)
                        .user(user).build());
            }
        }catch(IOException e){
            throw ScheduleRegisterException.EXCEPTION;
        }
        return Response.ok("파일 저장 성공");
    }

    public ResponseData<List<GetTodaySchedulesRes>> getTodaySchedules(DayOfWeek dayOfWeek) {
        List<GetTodaySchedulesRes> result = GetTodaySchedulesRes.of(scheduleService.getTodaySchedules(dayOfWeek));
        return ResponseData.ok("오늘 실이동 조회",result);
    }
}
