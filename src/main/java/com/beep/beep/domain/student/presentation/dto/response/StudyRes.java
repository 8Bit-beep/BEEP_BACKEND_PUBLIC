package com.beep.beep.domain.student.presentation.dto.response;

import com.beep.beep.domain.student.domain.Student;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public record StudyRes(String name, Integer grade, Integer cls, Integer num, boolean isExist, LocalDateTime modifiedDate) {
    public static List<StudyRes> of(List<Student> students) {
        return students.parallelStream()
                .map(StudyRes::of)
                .toList();
    }

    public static StudyRes of(Student student) {
        return new StudyRes(
                student.getUser().getName(),
                student.getGrade(),
                student.getCls(),
                student.getNum(),
                Objects.equals(student.getStudyRoom().getCode(), student.getRoom().getCode()),
                student.getModifiedDate()
        );
    }
}
