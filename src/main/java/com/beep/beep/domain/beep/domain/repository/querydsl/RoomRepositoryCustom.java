package com.beep.beep.domain.beep.domain.repository.querydsl;

import com.beep.beep.domain.beep.presentation.dto.Room;

import java.util.List;

public interface RoomRepositoryCustom {
    List<Room> roomListByName(String name);
}
