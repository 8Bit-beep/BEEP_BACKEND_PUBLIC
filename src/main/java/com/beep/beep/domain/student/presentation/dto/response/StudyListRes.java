package com.beep.beep.domain.student.presentation.dto.response;

import com.beep.beep.domain.student.domain.Student;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public record StudyListRes(String name, Integer grade, Integer cls, Integer num, boolean isExist, LocalDateTime modifiedDate) {
    public static List<StudyListRes> of(List<Student> students) {
        return students.parallelStream()
                .map(StudyListRes::of)
                .toList();
    }

    public static StudyListRes of(Student student) {
        return new StudyListRes(
                student.getUser().getName(),
                student.getGrade(),
                student.getCls(),
                student.getNum(),
                Objects.equals(student.getStudyRoom().getCode(), student.getRoom().getCode()),
                student.getModifiedDate()
        );
    }
}
