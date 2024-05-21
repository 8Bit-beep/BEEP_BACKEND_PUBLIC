package com.beep.beep.domain.beep.domain.repository;

import com.beep.beep.domain.beep.domain.RoomEntity;
import org.springframework.data.repository.CrudRepository;


public interface RoomRepository extends CrudRepository<RoomEntity,String> {
    RoomEntity findByCode(String code);
}
