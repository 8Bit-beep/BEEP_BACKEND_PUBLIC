package com.beep.beep.domain.teacher.presentation;

import com.beep.beep.domain.auth.presentation.dto.request.SignUpReq;
import com.beep.beep.domain.auth.service.AuthService;
import com.beep.beep.domain.teacher.presentation.dto.response.TeacherInfoRes;
import com.beep.beep.domain.teacher.usecase.TeacherUseCase;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.enums.UserType;
import com.beep.beep.domain.user.service.UserService;
import com.beep.beep.global.common.dto.response.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.beep.beep.domain.user.domain.enums.UserType.TEACHER;

@RestController
@RequiredArgsConstructor
@Tag(name = "선생님", description = "선생님 조회 API")
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherUseCase teacherUseCase;
    private final AuthService authService;

    @GetMapping("/info")
    @Operation(summary = "선생님프로필 조회", description = "선생님프로필 조회합니다. (teacher)")
    public ResponseData<TeacherInfoRes> teacherInfo() {
        return teacherUseCase.teacherInfo();
    }

    @PostMapping("account")
    public String readExcel(@RequestParam("file") MultipartFile file,@RequestParam("password") String password)
            throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

        for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {

            DataFormatter formatter = new DataFormatter();
            XSSFRow row = worksheet.getRow(i);

            String email = formatter.formatCellValue(row.getCell(0));
            String name = formatter.formatCellValue(row.getCell(1));

            authService.signUp(new SignUpReq(email,password,name, TEACHER));
        }
        return "redirect:/success";
    }
}
