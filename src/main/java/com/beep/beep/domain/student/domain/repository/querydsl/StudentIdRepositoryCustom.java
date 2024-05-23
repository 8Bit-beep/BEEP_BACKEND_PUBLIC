package com.beep.beep.domain.student.domain.repository.querydsl;

import com.beep.beep.domain.student.presentation.dto.response.ClsByGradeRes;

import java.util.List;

public interface StudentIdRepositoryCustom {

    List<ClsByGradeRes> clsListByGrade(int grade);

}
