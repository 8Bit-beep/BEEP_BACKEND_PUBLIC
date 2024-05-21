package com.beep.beep.domain.beep.domain.repository.querydsl;


import com.beep.beep.domain.beep.domain.RoomEntity;


import java.util.List;

public interface RoomRepositoryCustom {
    List<RoomEntity> findAllByName(String name);
}
