package com.beep.beep.domain.student.presentation.dto.response;

import com.beep.beep.domain.room.domain.Club;
import com.beep.beep.domain.student.domain.Student;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public record AttendListRes(String name, Integer grade, Integer cls, Integer num, Club club, boolean isExist, LocalDateTime modifiedDate) {
    public static List<AttendListRes> of(List<Student> students) {
        return students.parallelStream()
                .map(AttendListRes::of)
                .toList();
    }

    public static AttendListRes of(Student student) {
        String code = "";

        if(student.getRoom() != null) {
            code = student.getRoom().getCode();
        }


        return new AttendListRes(
                student.getUser().getName(),
                student.getGrade(),
                student.getCls(),
                student.getNum(),
                student.getStudyRoom().getClub(),
                Objects.equals(student.getStudyRoom().getCode(), code),
                student.getModifiedDate()
        );
    }

}
