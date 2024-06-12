package com.beep.beep.domain.beep.domain.repository.querydsl.impl;


import com.beep.beep.domain.beep.domain.repository.querydsl.RoomRepoCustom;
import com.beep.beep.domain.beep.presentation.dto.RoomVO;
import com.beep.beep.domain.beep.presentation.dto.response.RoomByFloorRes;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import static com.beep.beep.domain.beep.domain.QRoom.room;

@Repository
@RequiredArgsConstructor
public class RoomRepoCustomImpl implements RoomRepoCustom {

    private final JPAQueryFactory query;

    @Override
    public List<RoomVO> roomListByName(String name) {
        return query.select(Projections.constructor(RoomVO.class,
                        room.code,
                        room.floor,
                        room.name))
                .from(room)
                .where(room.name.contains(name))
                .fetch();
    }

    @Override
    public List<RoomByFloorRes> roomListByFloor(Integer floor) {
        return query.select(Projections.constructor(RoomByFloorRes.class,
                room.code,
                room.name))
                .from(room)
                .where(room.floor.eq(floor))
                .fetch();
    }

}
