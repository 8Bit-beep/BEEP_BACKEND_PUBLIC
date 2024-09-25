package com.beep.beep.domain.room.domain.repository;

import com.beep.beep.domain.room.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, String> {
    List<Room> findAllByFloor(Integer floor);
}
