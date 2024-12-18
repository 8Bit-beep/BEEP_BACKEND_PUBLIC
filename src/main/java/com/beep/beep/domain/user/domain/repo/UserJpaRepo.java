package com.beep.beep.domain.user.domain.repo;

import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.enums.RoomCode;
import io.lettuce.core.dynamic.annotation.Param;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

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


    @Query(value = "SELECT * FROM tb_user u " +
            "WHERE REPLACE(u.name, ' ', '') LIKE CONCAT('%', REPLACE(:keyword, ' ', ''), '%') " +
            "AND u.authority = 'STUDENT'", nativeQuery = true)
    List<User> findAllByName(@Param("keyword") String keyword);

    Optional<User> findByGradeAndClsAndNumAndName(Integer grade, Integer cls, Integer num, String name);

    Integer countByFixedRoom(RoomCode room);

    Integer countByCurrentRoom(RoomCode room);

    boolean existsByGradeAndClsAndNum(Integer grade, Integer cls, Integer num);
}
