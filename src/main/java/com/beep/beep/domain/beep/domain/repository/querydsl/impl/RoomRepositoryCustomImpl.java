package com.beep.beep.domain.beep.domain.repository.querydsl.impl;


import com.beep.beep.domain.beep.domain.RoomEntity;
import com.beep.beep.domain.beep.domain.repository.querydsl.RoomRepositoryCustom;
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
    public List<RoomEntity> findAllByName(String name) {
        return query.selectFrom(roomEntity)
                .where(roomEntity.name.contains(name))
                .fetch();
    }
}
