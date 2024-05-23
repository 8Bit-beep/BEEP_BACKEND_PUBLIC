package com.beep.beep.domain.beep.domain.repository;

import com.beep.beep.domain.beep.domain.Room;
import com.beep.beep.domain.beep.domain.repository.querydsl.RoomRepositoryCustom;
import org.springframework.data.repository.CrudRepository;


public interface RoomRepository extends CrudRepository<Room,String> , RoomRepositoryCustom {
}
