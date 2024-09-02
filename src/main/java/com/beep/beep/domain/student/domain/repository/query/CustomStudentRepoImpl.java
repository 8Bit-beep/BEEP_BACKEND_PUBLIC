package com.beep.beep.domain.student.domain.repository.query;

import com.beep.beep.domain.student.presentation.dto.request.MemberListReq;
import com.beep.beep.domain.student.presentation.dto.response.AttendListRes;
import com.beep.beep.domain.student.presentation.dto.response.MemberListRes;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.beep.beep.domain.room.domain.QRoom.room;
import static com.beep.beep.domain.student.domain.QStudent.student;

@Repository
@RequiredArgsConstructor
public class CustomStudentRepoImpl implements CustomStudentRepo {

    private final JPAQueryFactory query;

    @Override
    public List<AttendListRes> attendList(String code) {
        return query.select(Projections.constructor(AttendListRes.class,
                        student.name,
                        student.grade,
                        student.cls,
                        student.num))
                .from(student)
                .where(student.code.eq(code))
                .fetch();
    }

    @Override
    public List<MemberListRes> memberList(Integer grade,Integer cls) {
        return query.select(Projections.constructor(MemberListRes.class,
                        student.name.as("studentName"),
                        student.num.as("num"),
                        room.name.as("roomName"),
                        room.floor.as("floor")
                ))
                .from(student)
                .leftJoin(room).on(room.code.eq(student.code)).fetchJoin()
                .where(student.grade.eq(grade),student.cls.eq(cls))
                .fetch();
    }

}
