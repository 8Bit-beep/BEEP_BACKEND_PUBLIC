package com.beep.beep.domain.beep.domain.repository;

import com.beep.beep.domain.beep.domain.RoomEntity;
import com.beep.beep.domain.beep.presentation.dto.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends CrudRepository<RoomEntity,String> {
    Room findByCode(String code);

    @Query("SELECT s FROM RoomEntity s WHERE s.name LIKE CONCAT('%', :name, '%')")
    List<Room> findAllByName(@Param("name") String name);

}
