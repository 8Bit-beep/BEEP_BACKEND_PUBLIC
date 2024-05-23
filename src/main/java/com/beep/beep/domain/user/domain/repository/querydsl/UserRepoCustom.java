package com.beep.beep.domain.user.domain.repository.querydsl;


import com.beep.beep.domain.student.presentation.dto.response.StudentRes;
import com.beep.beep.domain.student.presentation.dto.response.StudentByNameRes;
import com.beep.beep.domain.teacher.presentation.dto.response.TeacherRes;
import com.beep.beep.domain.teacher.presentation.dto.response.TeacherByUserRes;
import com.beep.beep.domain.user.presentation.dto.UserVO;
import com.beep.beep.global.common.dto.request.PageRequest;

import java.util.List;

public interface UserRepoCustom {

    List<StudentRes> studentList(PageRequest request);

    List<TeacherRes> teacherList(PageRequest request);

    List<StudentByNameRes> studentListByName(String name);

    TeacherByUserRes teacherByUser(UserVO userVO);

    boolean existsByIdEmail(String id,String email);

}
