package com.beep.beep.domain.student.domain.repository.querydsl;

import com.beep.beep.domain.student.presentation.dto.response.ClsByGradeRes;
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
public class StudentIdRepositoryCustomImpl implements StudentIdRepositoryCustom {

    private final JPAQueryFactory query;

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
