package com.beep.beep.domain.student.presentation.dto.response;


import java.time.LocalDate;
import java.time.LocalDateTime;

public record MemberListRes(String studentName, Integer num, String roomName, Integer floor, LocalDateTime modifiedDate) {
}
