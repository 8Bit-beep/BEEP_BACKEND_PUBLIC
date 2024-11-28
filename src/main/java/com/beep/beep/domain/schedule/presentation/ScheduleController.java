package com.beep.beep.domain.schedule.presentation;

import com.beep.beep.domain.schedule.presentation.dto.response.GetTodaySchedulesRes;
import com.beep.beep.domain.schedule.usecase.ScheduleUseCase;
import com.beep.beep.global.common.dto.response.Response;
import com.beep.beep.global.common.dto.response.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.DayOfWeek;
import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
@Tag(name = "실이동 일정", description = "실이동 일정 API")
public class ScheduleController {

    private final ScheduleUseCase scheduleUseCase;

    @PostMapping("")
    @Operation(summary = "실이동 일정 저장", description = "엑셀파일로 실이동 일정 저장 (teacher)")
    public Response register(
            @RequestParam("file") MultipartFile file
    ){
        return scheduleUseCase.register(file);
    }

    @GetMapping("")
    @Operation(summary = "오늘 실이동 일정 조회", description = "오늘 실이동 일정 조회 (teacher)")
    public ResponseData<List<GetTodaySchedulesRes>> getTodaySchedules(
            @RequestParam DayOfWeek dayOfWeek
    ){
        return scheduleUseCase.getTodaySchedules(dayOfWeek);
    }

    @GetMapping("/excel")
    @Operation(summary = "엑셀 파일 다운로드", description = "엑셀 다운로드 (teacher)")
    public void getScheduleExcel(HttpServletResponse response){
        scheduleUseCase.getScheduleExcel(response);
    }

}
