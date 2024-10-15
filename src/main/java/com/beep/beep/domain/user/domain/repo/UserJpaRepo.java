package com.beep.beep.domain.user.domain.repo;

import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.enums.RoomCode;
import io.lettuce.core.dynamic.annotation.Param;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserJpaRepo extends JpaRepository<User,String> {
    List<User> findAllByCurrentRoom(RoomCode code);
    List<User> findAllByGradeAndCls(Integer grade, Integer cls);

    @Modifying
    @Transactional
    @Query("UPDATE User s SET s.currentRoom = :code")
    void updateAllRoomToNotFound(@Param("code") RoomCode code);

    List<User> findAllByFixedRoom(RoomCode code);

    @Query("SELECT u FROM User u WHERE u.fixedRoom IN :rooms")
    List<User> findAllByFixedRooms(List<RoomCode> rooms);
}
