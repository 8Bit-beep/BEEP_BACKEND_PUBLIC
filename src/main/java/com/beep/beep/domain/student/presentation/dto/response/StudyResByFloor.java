package com.beep.beep.domain.student.presentation.dto.response;

import com.beep.beep.domain.room.domain.Club;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.enums.RoomCode;

import java.time.LocalDateTime;
import java.util.List;

public record StudyResByFloor(
        String name,
        Integer grade,
        Integer cls,
        Integer num,
        RoomCode currentRoom,
        LocalDateTime lastUpdated,
        RoomCode fixedRoom,
        boolean isExist,
        Club club) {
    public static List<StudyResByFloor> of(List<User> users) {
        return users.parallelStream()
                .map(StudyResByFloor::of)
                .toList();
    }

    public static StudyResByFloor of(User user) {
        return new StudyResByFloor(
                user.getName(),
                user.getGrade(),
                user.getCls(),
                user.getNum(),
                user.getCurrentRoom(),
                user.getLastUpdated(),
                user.getFixedRoom(),
                user.getFixedRoom().equals(user.getCurrentRoom()),
                Club.of(user.getFixedRoom().getCode())
        );
    }
}
