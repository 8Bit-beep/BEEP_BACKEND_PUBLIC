package com.beep.beep.domain.student.domain.repository.querydsl;

import com.beep.beep.domain.student.presentation.dto.request.StudentByGradeClsReq;
import com.beep.beep.domain.student.presentation.dto.response.ClsByGradeRes;
import com.beep.beep.domain.student.presentation.dto.response.StudentByGradeClsRes;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.NumberExpression;
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
public class StudentIdRepoCustomImpl implements StudentIdRepoCustom {

    private final JPAQueryFactory query;

    @Override
    public List<StudentByGradeClsRes> studentListByGradeCls(StudentByGradeClsReq req) {
        return query.select(Projections.constructor(StudentByGradeClsRes.class,
                        user.idx,
                        user.name,
                        studentId.num,
                        room.floor,
                        room.name))
                .from(studentId)
                .innerJoin(attendance).on(attendance.userIdx.eq(studentId.userIdx))
                .innerJoin(room).on(room.code.eq(attendance.code))
                .innerJoin(user).on(user.idx.eq(studentId.userIdx))
                .where(studentId.grade.eq(req.getGrade()),studentId.cls.eq(req.getCls()))
                .orderBy(studentId.num.asc())
                .fetch();
    }

    @Override
    public List<ClsByGradeRes> clsListByGrade(int grade) {
        NumberExpression<Long> countExpression = studentId.count();

        return query
                .select(Projections.constructor(ClsByGradeRes.class,
                        studentId.cls,
                        countExpression))
                .from(studentId)
                .where(studentId.grade.eq(grade))
                .groupBy(studentId.cls)
                .fetch();
    }

}
