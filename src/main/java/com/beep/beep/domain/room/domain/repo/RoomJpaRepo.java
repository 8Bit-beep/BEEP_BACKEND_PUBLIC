package com.beep.beep.domain.room.domain.repo;

import com.beep.beep.domain.room.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomJpaRepo extends JpaRepository<Room, Long> {
    List<Room> findAllByFloor(Integer floor);
}
