package com.beep.beep.domain.room.service;

import com.beep.beep.domain.room.domain.Room;
import com.beep.beep.domain.room.domain.repo.RoomJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomJpaRepo roomJpaRepo;

    public List<Room> roomList(Integer floor){
        return roomJpaRepo.findAllByFloor(floor);
    }

    public List<Room> getRoomsByName(String keyword) {
        return roomJpaRepo.findAllByName(keyword);
    }
}
