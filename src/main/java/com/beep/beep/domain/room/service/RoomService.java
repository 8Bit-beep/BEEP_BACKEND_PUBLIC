package com.beep.beep.domain.room.service;

import com.beep.beep.domain.room.domain.Club;
import com.beep.beep.domain.room.domain.Room;
import com.beep.beep.domain.room.domain.repository.RoomRepository;
import com.beep.beep.domain.room.exception.RoomNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public Room findByCode(String code){
        return roomRepository.findById(code)
                .orElseThrow(() -> RoomNotFoundException.EXCEPTION);
    }

    public List<Room> roomList(Integer floor){
        return roomRepository.findAllByFloor(floor);
    }

    public Room findByClub(Club club){
        return roomRepository.findByClub(club)
                .orElseThrow(()-> RoomNotFoundException.EXCEPTION);
    }
}
