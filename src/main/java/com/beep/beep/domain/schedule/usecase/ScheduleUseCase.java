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
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.DayOfWeek;
import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ScheduleUseCase {

    private final ScheduleService scheduleService;
    private final UserService userService;

    public Response register(MultipartFile file){
        scheduleService.deleteAll();
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

    public void getScheduleExcel(HttpServletResponse response) {
        List<Schedule> schedules = scheduleService.findAll();

        // 엑셀 데이터 생성
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("실이동 명단");
        int rowNo = 0;

        Row headerRow = sheet.createRow(rowNo++);
        headerRow.createCell(0).setCellValue("학년");
        headerRow.createCell(1).setCellValue("반");
        headerRow.createCell(2).setCellValue("번호");
        headerRow.createCell(3).setCellValue("이름");
        headerRow.createCell(4).setCellValue("이유");
        headerRow.createCell(5).setCellValue("실");
        headerRow.createCell(6).setCellValue("요일");
        headerRow.createCell(7).setCellValue("교시");

        for(Schedule schedule : schedules){
            Row row = sheet.createRow(rowNo++);
            row.createCell(0).setCellValue(schedule.getUser().getGrade());
            row.createCell(1).setCellValue(schedule.getUser().getCls());
            row.createCell(2).setCellValue(schedule.getUser().getNum());
            row.createCell(3).setCellValue(schedule.getUser().getName());
            row.createCell(4).setCellValue(schedule.getCause());
            row.createCell(5).setCellValue(schedule.getRoom());
            row.createCell(6).setCellValue(schedule.getDayOfWeek().getValue());
            row.createCell(7).setCellValue(schedule.getTimeTable().toString());
        }

        // 엑셀 응답 설정
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition",
                "attachment; filename=\"" + URLEncoder.encode("제출양식.xlsx", StandardCharsets.UTF_8) + "\"");

        try {
            workbook.write(response.getOutputStream());
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
