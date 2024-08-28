package com.beep.beep.domain.room.domain.repository;

import com.beep.beep.domain.room.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, String> {
}
