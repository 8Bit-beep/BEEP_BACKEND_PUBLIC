package com.beep.beep.domain.student.presentation.dto.response;

import com.beep.beep.domain.student.domain.enums.RoomCode;

public record MemberListRes(String name, Integer num, RoomCode roomName,String room) {
}
