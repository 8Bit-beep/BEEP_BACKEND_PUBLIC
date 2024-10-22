package com.beep.beep.domain.student.presentation.dto.response;

import com.beep.beep.domain.room.presentation.dto.RoomRes;

import java.util.List;

public record GetStudentOrRoomRes(List<MemberListRes> students, List<RoomRes> rooms) {
}
