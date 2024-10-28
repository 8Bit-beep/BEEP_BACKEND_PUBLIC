package com.beep.beep.domain.student.presentation.dto.response;

import com.beep.beep.domain.room.presentation.dto.RoomRes;

import java.util.List;

/**
 * 학생이름/실이름 search 응답 dto
 * */
public record GetStudentOrRoomRes(List<MemberListRes> students, List<RoomRes> rooms) {
}
