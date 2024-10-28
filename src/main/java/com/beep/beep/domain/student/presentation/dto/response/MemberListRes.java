package com.beep.beep.domain.student.presentation.dto.response;


import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.enums.RoomCode;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 반별 조회 응답 dto
 * */
public record MemberListRes(
        String name,
        Integer num,
        RoomCode currentRoom,
        String floor,
        LocalDateTime modifiedDate) {

    public static List<MemberListRes> of(List<User> users) {
        return users.parallelStream()
                .map(MemberListRes::of)
                .toList();
    }

    public static MemberListRes of(User user) {
        return new MemberListRes(
                user.getName(),
                user.getNum(),
                user.getCurrentRoom(),
                user.getCurrentRoom().getCode().substring(0,1),
                user.getLastUpdated() == null ? LocalDateTime.MIN : user.getLastUpdated()
        );
    }
}
