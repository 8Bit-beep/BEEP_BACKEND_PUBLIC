package com.beep.beep.domain.student.presentation.dto.response;

import com.beep.beep.domain.user.domain.User;

import java.time.LocalDateTime;
import java.util.List;

public record StudyListRes(String name, Integer grade, Integer cls, Integer num, boolean isExist, LocalDateTime modifiedDate) {
    public static List<StudyListRes> of(List<User> users) {
        return users.parallelStream()
                .map(StudyListRes::of)
                .toList();
    }

    public static StudyListRes of(User user) {
        return new StudyListRes(
                user.getName(),
                user.getGrade(),
                user.getCls(),
                user.getNum(),
                user.getFixedRoom().equals(user.getCurrentRoom()),
                user.getLastUpdated() == null ? LocalDateTime.MIN : user.getLastUpdated()
        );
    }
}
