package com.beep.beep.domain.teacher.presentation.dto.response;

import com.beep.beep.domain.student.domain.StudentId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder @AllArgsConstructor
public class GetClsResponse {
    private int cls;
    private int headCount;

    public static GetClsResponse of(int cls,int headCount){
        return GetClsResponse.builder()
                .cls(cls)
                .headCount(headCount).build();
    }

}
