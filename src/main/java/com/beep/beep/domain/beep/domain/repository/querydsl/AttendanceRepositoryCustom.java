package com.beep.beep.domain.beep.domain.repository.querydsl;

import com.beep.beep.domain.beep.presentation.dto.response.GetAttendanceResponse;
import com.beep.beep.domain.student.presentation.dto.response.StudentInfoResponse;
import com.beep.beep.domain.user.presentation.dto.User;

import java.util.List;

public interface AttendanceRepositoryCustom {
    List<GetAttendanceResponse> attendanceListByCode(String code);

//    StudentInfoResponse studentInfo(User user);
}
