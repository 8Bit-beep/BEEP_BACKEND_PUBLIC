package com.beep.beep.domain.beep.domain.repository.querydsl.impl;


import com.beep.beep.domain.beep.domain.repository.querydsl.AttendanceRepositoryCustom;
import com.beep.beep.domain.beep.presentation.dto.response.AttendanceByCodeRes;
import com.beep.beep.domain.student.presentation.dto.response.StudentByUserRes;
import com.beep.beep.domain.user.presentation.dto.UserVO;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.beep.beep.domain.beep.domain.QAttendance.attendance;
import static com.beep.beep.domain.beep.domain.QRoom.room;
import static com.beep.beep.domain.student.domain.QStudentId.studentId;
import static com.beep.beep.domain.user.domain.QUser.user;

@Repository
@RequiredArgsConstructor
public class AttendanceRepoCustomImpl implements AttendanceRepositoryCustom {

    private final JPAQueryFactory query;

    // room d에 누가 있는지 쿼리
    // code로 attendance조회 , user -> name , room -> roomName , studentId 정보
    @Override
    public List<AttendanceByCodeRes> attendanceListByCode(String code) {
        return query
                .select(attendanceListConstructorExpression())
                .from(attendance)
                .innerJoin(user).on(user.idx.eq(attendance.userIdx))
                .innerJoin(studentId).on(studentId.userIdx.eq(attendance.userIdx))
                .innerJoin(room).on(room.code.eq(attendance.code))
                .where(attendance.code.eq(code))
                .orderBy(attendance.userIdx.asc())
                .fetch();
    }

    @Override
    public StudentByUserRes studentByUser(UserVO userVO) {
        return query.select(studentInfoConstructorExpression(userVO))
                .from(attendance)
                .innerJoin(user).on(user.idx.eq(attendance.userIdx))
                .innerJoin(studentId).on(studentId.userIdx.eq(attendance.userIdx))
                .innerJoin(room).on(room.code.eq(attendance.code))
                .where(attendance.userIdx.eq(userVO.getIdx()))
                .fetchFirst();
    }

    private ConstructorExpression<AttendanceByCodeRes> attendanceListConstructorExpression() {
        return Projections.constructor(AttendanceByCodeRes.class,
                user.name,
                studentId.grade,
                studentId.cls,
                studentId.num);
    }

    private ConstructorExpression<StudentByUserRes> studentInfoConstructorExpression(UserVO vo) {
        return Projections.constructor(StudentByUserRes.class,
                user.name,
                user.email,
                studentId.grade,
                studentId.cls,
                studentId.num,
                room.name);
    }

}
