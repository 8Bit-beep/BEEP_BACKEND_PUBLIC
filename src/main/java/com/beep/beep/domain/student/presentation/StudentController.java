package com.beep.beep.domain.student.presentation;

import com.beep.beep.domain.room.domain.Club;
import com.beep.beep.domain.student.domain.Student;
import com.beep.beep.domain.student.presentation.dto.request.AttendReq;
import com.beep.beep.domain.student.presentation.dto.request.StudentSignUpByExcel;
import com.beep.beep.domain.student.presentation.dto.request.StudentSignUpReq;
import com.beep.beep.domain.student.presentation.dto.response.AttendListRes;
import com.beep.beep.domain.student.presentation.dto.response.AttendRes;
import com.beep.beep.domain.student.presentation.dto.response.MemberListRes;
import com.beep.beep.domain.student.presentation.dto.response.StudentCodeRes;
import com.beep.beep.domain.student.presentation.dto.response.StudentInfoRes;
import com.beep.beep.domain.student.presentation.dto.response.StudyListRes;
import com.beep.beep.domain.student.usecase.StudentUseCase;
import com.beep.beep.global.common.dto.response.Response;
import com.beep.beep.global.common.dto.response.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@Tag(name = "학생", description = "학생 조회 API")
@RequestMapping("/student")
public class StudentController {

    private final StudentUseCase studentUseCase;

    @PostMapping("/info")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "학생 기본정보 저장", description = "회원가입 시 학생 기본 정보를 저장합니다. (unauthenticated)")
    public Response studentSignUp(
            @RequestBody StudentSignUpReq req
    ){
        return studentUseCase.signUp(req);
    }

    @GetMapping("/info")
    @Operation(summary = "학생프로필 조회", description = "학생프로필 조회입니다 (student)")
    public ResponseData<StudentInfoRes> studentInfo() {
        return studentUseCase.studentInfo();
    }

    @GetMapping("/code")
    @Operation(summary = "학생 실 코드 조회", description = "학생 실 코드를 조회합니다 (student)")
    public ResponseData<StudentCodeRes> studentCode() {
        return studentUseCase.studentCode();
    }

    @Async
    @PatchMapping("/attend")
    @Operation(summary = "출석 요청", description = "입실/퇴실을 요청합니다.(student)")
    public CompletableFuture<ResponseData<AttendRes>> attend(
            @RequestBody AttendReq req
    ) {
        return studentUseCase.attend(req);
    }

    @GetMapping("/attend-list")
    @Operation(summary = "출석부 조회", description = "실 코드로 입실한 학생목록 조회합니다. (teacher)")
    public ResponseData<List<AttendListRes>> attendList(
            @RequestParam String code
    ){
        return studentUseCase.attendList(code);
    }

    @GetMapping("/member-list")
    @Operation(summary = "반 구성원 조회", description = "학년-반으로 반 구성원 목록을 조회합니다. (teacher)")
    public ResponseData<List<MemberListRes>> memberList(
            @RequestParam Integer grade,
            @RequestParam Integer cls
    ){
        return studentUseCase.memberList(grade,cls);
    }

    @GetMapping("/study-list")
    @Operation(summary = "스터디 구성원 조회", description = "스터디 출석정보 구성원 조회합니다.(teacher)")
    public ResponseData<List<StudyListRes>> studyList(
            @RequestParam Club club
    ){
        return studentUseCase.studyList(club);
    }

    @PostMapping("update")
    @Transactional(rollbackFor = Exception.class)
    public String readExcel(@RequestParam("file") MultipartFile file) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

        for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {

            DataFormatter formatter = new DataFormatter();
            XSSFRow row = worksheet.getRow(i);

            // 엑셀을 옮겨서 보낸다 -> 값을 수정해서 다시 저장
            String email = formatter.formatCellValue(row.getCell(0));
            Integer grade = Integer.valueOf(formatter.formatCellValue(row.getCell(2)));
            Integer cls = Integer.valueOf(formatter.formatCellValue(row.getCell(3)));
            Integer num = Integer.valueOf(formatter.formatCellValue(row.getCell(4)));
            String studyCode = formatter.formatCellValue(row.getCell(6));
            String changed = formatter.formatCellValue(row.getCell(7));

            if(Objects.equals(changed, "1"))
                studentUseCase.updateByExcel(new StudentSignUpByExcel(email,grade,cls,num,studyCode));
        }
        return "redirect:/success";
    }

    @GetMapping("excel")
    public String downloadExcel(HttpServletResponse response) throws IOException {
        // 엑셀 데이터 생성
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("고객 명단");
        int rowNo = 0;

        Row headerRow = sheet.createRow(rowNo++);
        headerRow.createCell(0).setCellValue("이메일");
        headerRow.createCell(1).setCellValue("이름");
        headerRow.createCell(2).setCellValue("학년");
        headerRow.createCell(3).setCellValue("반");
        headerRow.createCell(4).setCellValue("번호");
        headerRow.createCell(5).setCellValue("스터디");
        headerRow.createCell(6).setCellValue("실코드(관리자가 채울것)");

        // 비즈니스 로직 수행
        List<Student> studentList = studentUseCase.findAll();

        for (Student student : studentList) {
            Row row = sheet.createRow(rowNo++);
            row.createCell(0).setCellValue(student.getUser().getEmail());
            row.createCell(1).setCellValue(student.getUser().getName());
            row.createCell(2).setCellValue(student.getGrade());
            row.createCell(3).setCellValue(student.getCls());
            row.createCell(4).setCellValue(student.getNum());
            row.createCell(5).setCellValue(String.valueOf(student.getStudyRoom().getClub()));
        }

        // 엑셀 응답 설정
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition",
                "attachment; filename=\"" + URLEncoder.encode("삑 학생-스터디 리스트.xlsx", StandardCharsets.UTF_8) + "\"");

        workbook.write(response.getOutputStream());
        workbook.close();

        return "redirect:/success";
    }


}
