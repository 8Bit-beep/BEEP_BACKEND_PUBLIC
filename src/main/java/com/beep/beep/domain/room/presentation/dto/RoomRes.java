package com.beep.beep.domain.room.presentation.dto;

import com.beep.beep.domain.room.domain.Room;
import com.beep.beep.domain.user.domain.enums.RoomCode;

import java.util.List;

public record RoomRes(
        RoomCode roomCode,
        String code
) {
    public static List<RoomRes> of(List<Room> rooms) {
        return rooms.parallelStream()
                .map(RoomRes::of)
                .toList();
    }

    public static RoomRes of(Room room) {
        return new RoomRes(
                room.getName(),
                room.getCode()
        );
    }
}
