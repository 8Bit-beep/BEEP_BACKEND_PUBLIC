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
    public List<MemberListRes> memberList(MemberListReq req) {
        return query.select(Projections.fields(MemberListRes.class,
                        student.name.as("name"),
                        student.num.as("num"),
                        room.name,
                room.floor
                ))
                .from(student)
                .innerJoin(room).on(room.code.eq(student.code))
                .where(student.grade.eq(req.grade()), student.cls.eq(req.cls()))
                .orderBy(student.num.asc())
                .fetch();
    }

}
