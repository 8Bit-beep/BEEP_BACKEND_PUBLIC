package com.beep.beep.domain.beep.domain.repository;

import com.beep.beep.domain.beep.domain.RoomEntity;
import com.beep.beep.domain.beep.domain.repository.querydsl.RoomRepositoryCustom;
import org.springframework.data.repository.CrudRepository;


public interface RoomRepository extends CrudRepository<RoomEntity,String> , RoomRepositoryCustom {
    RoomEntity findByCode(String code);
}
