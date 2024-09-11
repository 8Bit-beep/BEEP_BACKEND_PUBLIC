package com.beep.beep.domain.student.presentation.dto.response;

import com.beep.beep.domain.student.domain.Student;
import com.beep.beep.domain.student.exception.NotAttendedException;
import lombok.Builder;


@Builder
public record StudentCodeRes(String code) {
    public static StudentCodeRes of(Student student) {
        try {
            return StudentCodeRes.builder()
                    .code(student.getRoom().getCode())
                    .build();
        }catch(NullPointerException e){
            throw NotAttendedException.EXCEPTION;
        }
    }
}
