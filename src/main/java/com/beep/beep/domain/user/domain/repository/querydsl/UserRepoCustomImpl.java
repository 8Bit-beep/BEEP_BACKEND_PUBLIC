package com.beep.beep.domain.user.domain.repository.querydsl;

import com.beep.beep.domain.student.presentation.dto.response.StudentRes;
import com.beep.beep.domain.student.presentation.dto.response.StudentByNameRes;
import com.beep.beep.domain.teacher.presentation.dto.response.TeacherRes;
import com.beep.beep.domain.teacher.presentation.dto.response.TeacherByUserRes;
import com.beep.beep.domain.user.presentation.dto.UserVO;
import com.beep.beep.global.common.dto.request.PageRequest;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.beep.beep.domain.beep.domain.QAttendance.attendance;
import static com.beep.beep.domain.beep.domain.QRoom.room;
import static com.beep.beep.domain.student.domain.QStudentId.studentId;
import static com.beep.beep.domain.teacher.domain.QJob.job1;
import static com.beep.beep.domain.user.domain.QUser.user;
import static com.beep.beep.domain.user.domain.enums.UserType.STUDENT;
import static com.beep.beep.domain.user.domain.enums.UserType.TEACHER;


@Repository
@RequiredArgsConstructor
public class UserRepoCustomImpl implements UserRepoCustom {

    private final JPAQueryFactory query;

    @Override
    public List<StudentRes> studentList(PageRequest request) {
        return query
                .select(studentListConstructorExpression())
                .from(user)
                .innerJoin(studentId).on(studentId.userIdx.eq(user.idx))
                .innerJoin(attendance).on(attendance.userIdx.eq(user.idx))
                .where(user.authority.eq(STUDENT))
                .offset((request.getPage() - 1) * request.getSize())
                .limit(request.getSize())
                .orderBy(user.idx.asc())
                .fetch();
    }

    @Override
    public List<TeacherRes> teacherList(PageRequest request){
        return query
                .select(teacherListConstructorExpression())
                .from(user)
                .innerJoin(job1).on(job1.userIdx.eq(user.idx))
                .where(user.authority.eq(TEACHER))
                .offset((request.getPage() - 1) * request.getSize())
                .limit(request.getSize())
                .orderBy(user.idx.asc())
                .fetch();
    }

    @Override
    public TeacherByUserRes teacherByUser(UserVO userVO){
        return query
                .select(teacherByUserConstructorExpression())
                .from(user)
                .innerJoin(job1).on(job1.userIdx.eq(user.idx))
                .where(user.idx.eq(userVO.getIdx()))
                .fetchFirst();
    }

    @Override
    public boolean existsByIdEmail(String id, String email) {
        return query.selectFrom(user)
                .where(user.email.eq(email), user.id.eq(id))
                .fetchFirst() != null;
    }

    @Override
    public List<StudentByNameRes> studentListByName(String name) {
        return query.select(studentListByNameConstructorExpression())
                .from(studentId)
                .innerJoin(attendance).on(attendance.userIdx.eq(studentId.userIdx))
                .innerJoin(room).on(room.code.eq(attendance.code))
                .innerJoin(user).on(user.idx.eq(studentId.userIdx))
                .where(user.name.contains(name))
                .fetch();
    }

    private ConstructorExpression<StudentByNameRes> studentListByNameConstructorExpression() {
        return Projections.constructor(StudentByNameRes.class,
                user.name,
                studentId.grade,
                studentId.cls,
                studentId.num,
                room.name);
    }

    private ConstructorExpression<TeacherByUserRes> teacherByUserConstructorExpression() {
        return Projections.constructor(TeacherByUserRes.class,
                user.name,
                user.email,
                job1.department,
                job1.job);
    }

    private ConstructorExpression<TeacherRes> teacherListConstructorExpression() {
        return Projections.constructor(TeacherRes.class,
                user.idx,
                user.id,
                user.name,
                user.email,
                job1.department,
                job1.job);
    }

    private ConstructorExpression<StudentRes> studentListConstructorExpression() {
        return Projections.constructor(StudentRes.class,
                user.idx,
                user.id,
                user.name,
                user.email,
                studentId.grade,
                studentId.cls,
                studentId.num,
                attendance.code);
    }

}