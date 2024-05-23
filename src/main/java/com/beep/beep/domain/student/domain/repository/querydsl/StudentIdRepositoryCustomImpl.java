package com.beep.beep.domain.student.domain.repository.querydsl;

import com.beep.beep.domain.student.domain.StudentIdEntity;
import com.beep.beep.domain.student.presentation.dto.response.StudentInfoResponse;
import com.beep.beep.domain.user.presentation.dto.User;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.beep.beep.domain.beep.domain.QRoomEntity.roomEntity;
import static com.beep.beep.domain.student.domain.QStudentIdEntity.studentIdEntity;

@Repository
@RequiredArgsConstructor
public class StudentIdRepositoryCustomImpl implements StudentIdRepositoryCustom {

    private final JPAQueryFactory query;

//    @Override
//    public StudentInfoResponse studentInfo(User user) {
//        return query.select(studentInfoConstructorExpression());
//    }
//
//    private ConstructorExpression<StudentInfoResponse> studentInfoConstructorExpression(User user) {
//        return Projections.constructor(StudentInfoResponse.class,
//                studentIdEntity.grade,
//                studentIdEntity.cls,
//                studentIdEntity.num,
//                roomEntity.name);
//    }
//

//    @Override
//    public List<StudentIdEntity> findByGradeAndCls(int grade, int cls) {
//        return query.selectFrom(studentIdEntity)
//                .where(studentIdEntity.grade.eq(grade),studentIdEntity.cls.eq(cls))
//                .fetch();
//    }

//    @Override
//    public List<Integer> findClsByGrade(int grade) {
//        return query.select(studentIdEntity.cls)
//                .from(studentIdEntity)
//                .where(studentIdEntity.grade.eq(grade)).fetch();
//    }

//    @Override
//    public int countByCls(int grade, int cls) {
//        return query.select(studentIdEntity.count())
//                .from(studentIdEntity)
//                .where(studentIdEntity.grade.eq(grade)
//                        .and(studentIdEntity.cls.eq(cls)))
//                .fetchOne();
//    }

//    public List<RoomEntity> findAllByName(String name) {
//        return query.selectFrom(roomEntity)
//                .where(roomEntity.name.contains(name))
//                .fetch();
//    }
}