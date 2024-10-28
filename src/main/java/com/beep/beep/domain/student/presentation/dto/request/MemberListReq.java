package com.beep.beep.domain.student.presentation.dto.request;

/**
 * 반별 조회 요청 dto
 * */
public record MemberListReq(
        Integer grade,
        Integer cls
) {
}
