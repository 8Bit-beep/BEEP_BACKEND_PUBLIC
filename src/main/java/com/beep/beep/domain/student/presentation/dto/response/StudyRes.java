package com.beep.beep.domain.student.presentation.dto.response;

import com.beep.beep.domain.user.domain.User;

import java.time.LocalDateTime;
import java.util.List;

public record StudyRes(
        String name,
        Integer grade,
        Integer cls,
        Integer num,
        boolean isExist,
        LocalDateTime modifiedDate) {
    public static List<StudyRes> of(List<User> users) {
        return users.parallelStream()
                .map(StudyRes::of)
                .toList();
    }

    public static StudyRes of(User user) {
        return new StudyRes(
                user.getName(),
                user.getGrade(),
                user.getCls(),
                user.getNum(),
                user.getFixedRoom().equals(user.getCurrentRoom()),
                user.getLastUpdated() == null ? LocalDateTime.MIN : user.getLastUpdated()
        );
    }
}
