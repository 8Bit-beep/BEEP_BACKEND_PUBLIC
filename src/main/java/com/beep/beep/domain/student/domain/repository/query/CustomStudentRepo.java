package com.beep.beep.domain.student.domain.repository.query;

import com.beep.beep.domain.student.presentation.dto.request.MemberListReq;
import com.beep.beep.domain.student.presentation.dto.response.AttendListRes;
import com.beep.beep.domain.student.presentation.dto.response.MemberListRes;

import java.util.List;

public interface CustomStudentRepo {
    List<AttendListRes> attendList(String code);
    List<MemberListRes> memberList(MemberListReq req);
}
