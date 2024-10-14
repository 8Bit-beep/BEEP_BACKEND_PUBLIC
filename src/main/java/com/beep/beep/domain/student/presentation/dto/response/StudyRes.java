package com.beep.beep.domain.student.presentation.dto.response;

import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.enums.RoomCode;

import java.time.LocalDateTime;
import java.util.List;

public record StudyRes(
        RoomCode roomName,
        String name,
        Integer grade,
        Integer cls,
        Integer num,
        boolean isExist,
        LocalDateTime modifiedDate) {
    public static List<StudyRes> of(RoomCode requestedRoom,List<User> users) {
        return users.parallelStream()
                .map(user -> of(requestedRoom,user))
                .toList();
    }

    public static StudyRes of(RoomCode requestedRoom, User user) {
        return new StudyRes(
                requestedRoom,
                user.getName(),
                user.getGrade(),
                user.getCls(),
                user.getNum(),
                user.getFixedRoom().equals(user.getCurrentRoom()),
                user.getLastUpdated()
        );
    }
}
