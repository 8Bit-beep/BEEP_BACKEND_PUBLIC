package com.beep.beep.domain.student.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GetClsResponse {
    private int cls;
    private int headCount;

}
