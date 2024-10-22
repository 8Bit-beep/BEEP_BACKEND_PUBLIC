package com.beep.beep.domain.room.usecase;

import com.beep.beep.domain.room.presentation.dto.RoomRes;
import com.beep.beep.domain.room.service.RoomService;
import com.beep.beep.global.common.dto.response.Response;
import com.beep.beep.global.common.dto.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoomUseCase {

    private final RoomService roomService;

    public ResponseData<List<RoomRes>> roomList(Integer floor){
        List<RoomRes> result = RoomRes.of(roomService.roomList(floor));
        return ResponseData.ok("실 리스트 조회 성공",result);
    }
}
