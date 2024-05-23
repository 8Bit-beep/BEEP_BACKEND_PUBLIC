package com.beep.beep.domain.student.domain.repository.querydsl;

import com.beep.beep.domain.student.presentation.dto.request.StudentByGradeClsReq;
import com.beep.beep.domain.student.presentation.dto.response.ClsByGradeRes;
import com.beep.beep.domain.student.presentation.dto.response.StudentByGradeClsRes;

import java.util.List;

public interface StudentIdRepoCustom {

    List<ClsByGradeRes> clsListByGrade(int grade);

    List<StudentByGradeClsRes> studentListByGradeCls(StudentByGradeClsReq req);

}
