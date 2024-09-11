package com.beep.beep.domain.student.presentation.dto.response;


import com.beep.beep.domain.student.domain.Student;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public record MemberListRes(String name, Integer num, String roomName, Integer floor, LocalDateTime modifiedDate) {
    public static List<MemberListRes> of(List<Student> students) {
        return students.parallelStream()
                .map(MemberListRes::of)
                .toList();
    }

    public static MemberListRes of(Student student) {
        return new MemberListRes(
                student.getUser().getName(),
                student.getNum(),
                student.getRoom().getName(),
                student.getRoom().getFloor(),
                student.getModifiedDate()
        );
    }
}
