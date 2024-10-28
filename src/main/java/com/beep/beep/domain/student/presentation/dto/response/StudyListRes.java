package com.beep.beep.domain.student.presentation.dto.response;

import com.beep.beep.domain.user.domain.enums.RoomCode;

import java.util.List;

/**
 * study로 출석조회  dto
 * */
public record StudyListRes(
        RoomCode roomName,
        List<StudyRes> studyList
) {
    public static StudyListRes of(RoomCode roomName, List<StudyRes> studyList) {
        return new StudyListRes(roomName, studyList);
    }
}
