package com.beep.beep.domain.teacher.service;

import com.beep.beep.domain.teacher.domain.repository.JobRepository;
import com.beep.beep.domain.teacher.mapper.TeacherMapper;
import com.beep.beep.domain.teacher.presentation.dto.request.SaveJobReq;
import com.beep.beep.domain.teacher.presentation.dto.response.TeacherRes;
import com.beep.beep.domain.teacher.presentation.dto.response.TeacherByUserRes;
import com.beep.beep.domain.user.domain.repository.UserRepository;
import com.beep.beep.global.common.dto.request.PageRequest;
import com.beep.beep.global.common.service.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherMapper teacherMapper;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private final UserUtil userUtil;

    public void saveJob(SaveJobReq req){
        jobRepository.save(teacherMapper.toJob(userUtil.findUserByEmail(req.getEmail()),req));
    }

    public List<TeacherRes> teacherList(PageRequest request) {
        return userRepository.teacherList(request);
    }

    public TeacherByUserRes teacherByUser(){
        return userRepository.teacherByUser(userUtil.getCurrentUser());
    }

}
