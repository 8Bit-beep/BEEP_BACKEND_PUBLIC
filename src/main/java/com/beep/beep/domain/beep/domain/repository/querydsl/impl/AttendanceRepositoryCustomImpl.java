package com.beep.beep.domain.beep.domain.repository.querydsl.impl;


import com.beep.beep.domain.beep.domain.repository.querydsl.AttendanceRepositoryCustom;
import com.beep.beep.domain.beep.presentation.dto.response.GetAttendanceResponse;
import com.beep.beep.domain.student.presentation.dto.response.StudentInfoResponse;
import com.beep.beep.domain.user.presentation.dto.User;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.beep.beep.domain.beep.domain.QAttendanceEntity.attendanceEntity;
import static com.beep.beep.domain.beep.domain.QRoomEntity.roomEntity;
import static com.beep.beep.domain.student.domain.QStudentIdEntity.studentIdEntity;
import static com.beep.beep.domain.user.domain.QUserEntity.userEntity;

@Repository
@RequiredArgsConstructor
public class AttendanceRepositoryCustomImpl implements AttendanceRepositoryCustom {

    private final JPAQueryFactory query;

    // room d에 누가 있는지 쿼리
    // code로 attendance조회 , user -> name , room -> roomName , studentId 정보
    @Override
    public List<GetAttendanceResponse> attendanceListByCode(String code) {
        return query
                .select(attendanceListConstructorExpression())
                .from(attendanceEntity)
                .where(attendanceEntity.code.eq(code))
                .orderBy(attendanceEntity.userIdx.asc())
                .fetch();
    }

    @Override
    public StudentInfoResponse studentInfo(User user) {
        return query.select(studentInfoConstructorExpression(user))
                .from(attendanceEntity)
                .where(attendanceEntity.userIdx.eq(user.getIdx()))
                .fetch();
    }

    private ConstructorExpression<GetAttendanceResponse> attendanceListConstructorExpression() {
        return Projections.constructor(GetAttendanceResponse.class,
                userEntity.name,
                studentIdEntity.grade,
                studentIdEntity.cls,
                studentIdEntity.num);
    }



    private ConstructorExpression<StudentInfoResponse> studentInfoConstructorExpression(User user) {
        return Projections.constructor(StudentInfoResponse.class,
                studentIdEntity.grade,
                studentIdEntity.cls,
                studentIdEntity.num,
                roomEntity.name);
    }



}
