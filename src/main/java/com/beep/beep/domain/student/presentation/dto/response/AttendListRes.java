package com.beep.beep.domain.student.presentation.dto.response;

import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.enums.RoomCode;

import java.time.LocalDateTime;
import java.util.List;


/**
 * 실이름으로 출석부 조회 응답 dto
 * */
public record AttendListRes(
        String name,
        Integer grade,
        Integer cls,
        Integer num,
        RoomCode fixedRoom,
        boolean isExist,
        LocalDateTime modifiedDate) {

    public static List<AttendListRes> of(List<User> users) {
        return users.parallelStream()
                .map(AttendListRes::of)
                .toList();
    }

    public static AttendListRes of(User user) {
        return new AttendListRes(
                user.getName(),
                user.getGrade(),
                user.getCls(),
                user.getNum(),
                user.getFixedRoom(),
                user.getFixedRoom().equals(user.getCurrentRoom()),
                user.getLastUpdated() == null ? LocalDateTime.MIN : user.getLastUpdated()
        );
    }

}
