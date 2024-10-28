package com.beep.beep.domain.room.domain.repo;

import com.beep.beep.domain.room.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomJpaRepo extends JpaRepository<Room, Long> {
    List<Room> findAllByFloor(Integer floor);

    /**
     * 실이름 공백 제거 후 조회
     * */
    @Query(value = "SELECT * FROM tb_room r " +
            "WHERE REPLACE(r.name, ' ', '') LIKE CONCAT('%', REPLACE(:keyword, ' ', ''), '%')", nativeQuery = true)
    List<Room> findAllByName(String keyword);
}
