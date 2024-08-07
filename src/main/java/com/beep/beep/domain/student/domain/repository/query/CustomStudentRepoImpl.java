package com.beep.beep.domain.student.domain.repository.query;

import com.beep.beep.domain.student.domain.enums.RoomCode;
import com.beep.beep.domain.student.presentation.dto.request.MemberListReq;
import com.beep.beep.domain.student.presentation.dto.response.AttendListRes;
import com.beep.beep.domain.student.presentation.dto.response.MemberListRes;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomStudentRepoImpl implements CustomStudentRepo {

    private final JPAQueryFactory query;

    @Override
    public List<AttendListRes> attendList(RoomCode code) {
//        return query.select(Projections.constructor(AttendListRes.class,
//                        student.name,
//                        student.grade,
//                        student.cls,
//                        student.num))
//                .from(student)
//                .where(student.code.eq(code));
        return List.of();
    }

    @Override
    public List<MemberListRes> memberList(MemberListReq req) {
//        return query.select(Projections.constructor(MemberListRes.class,
//                        student.name,
//                        student.num,
//                        student.code,
//                        student.code.getCode()))
//                .from(student)
//                .where(student.grade.eq(req.grade()),student.cls.eq(req.cls()));
        return List.of();
    }


}
