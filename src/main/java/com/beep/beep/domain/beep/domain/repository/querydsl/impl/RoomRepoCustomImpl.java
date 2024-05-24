package com.beep.beep.domain.beep.domain.repository.querydsl.impl;


import com.beep.beep.domain.beep.domain.repository.querydsl.RoomRepoCustom;
import com.beep.beep.domain.beep.presentation.dto.RoomVO;
import com.querydsl.core.types.ConstructorExpression;
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
                        room.name,
                        room.floor))
                .from(room)
                .where(room.name.contains(name))
                .fetch();
    }

}
