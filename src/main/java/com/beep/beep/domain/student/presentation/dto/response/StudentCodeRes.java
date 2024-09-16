package com.beep.beep.domain.student.presentation.dto.response;

import com.beep.beep.domain.student.domain.Student;
import com.beep.beep.domain.student.exception.NotAttendedException;


public record StudentCodeRes(String code) {
    public static StudentCodeRes of(Student student) {
        try {
            return new StudentCodeRes(student.getRoom().getCode());
        }catch(NullPointerException e){
            throw NotAttendedException.EXCEPTION;
        }
    }
}
