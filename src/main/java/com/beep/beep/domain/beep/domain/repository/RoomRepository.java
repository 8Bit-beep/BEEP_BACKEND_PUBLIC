package com.beep.beep.domain.beep.domain.repository;

import com.beep.beep.domain.beep.domain.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends CrudRepository<Room,String> {
    Room findByCode(String code);

    @Query("SELECT s FROM Room s WHERE s.name LIKE CONCAT('%', :name, '%')")
    List<Room> findAllByName(@Param("name") String name);

}
