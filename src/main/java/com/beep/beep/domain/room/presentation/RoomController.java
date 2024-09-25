package com.beep.beep.domain.room.presentation;

import com.beep.beep.domain.room.usecase.RoomUseCase;
import com.beep.beep.global.common.dto.response.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomUseCase roomUseCase;

    @GetMapping("")
    @Operation(summary = "층별 정보 조회",description = "층을 보내면 그 층에 해당하는 실을 반환합니다.(teacher)")
    public ResponseData<String> roomList(
            @RequestParam Integer floor
    ){
        return roomUseCase.roomList(floor);
    }

}
