package com.beep.beep.domain.room.presentation.dto.response;

import com.beep.beep.domain.room.domain.Room;

import java.util.List;

public record RoomRes(String code,String name) {

    public static List<RoomRes> of(List<Room> rooms) {
        return rooms.parallelStream()
                .map(RoomRes::of)
                .toList();
    }

    public static RoomRes of(Room room) {
        return new RoomRes(
                room.getCode(),
                room.getName()
        );
    }

}
