package com.beep.beep.domain.beep.domain.repository.querydsl.impl;


import com.beep.beep.domain.beep.domain.repository.querydsl.RoomRepositoryCustom;
import com.beep.beep.domain.beep.presentation.dto.Attendance;
import com.beep.beep.domain.beep.presentation.dto.Room;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import static com.beep.beep.domain.beep.domain.QRoomEntity.roomEntity;

@Repository
@RequiredArgsConstructor
public class RoomRepositoryCustomImpl implements RoomRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public List<Room> roomListByName(String name) {
        return query.select(roomConstructorExpression())
                .from(roomEntity)
                .where(roomEntity.name.contains(name))
                .fetch();
    }


    private ConstructorExpression<Room> roomConstructorExpression() {
        return Projections.constructor(Room.class,
                roomEntity.code,
                roomEntity.name,
                roomEntity.floor);
    }

}
