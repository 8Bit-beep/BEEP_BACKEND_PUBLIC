package com.beep.beep.domain.room.presentation;

import com.beep.beep.domain.room.presentation.dto.RoomRes;
import com.beep.beep.domain.room.usecase.RoomUseCase;
import com.beep.beep.global.common.dto.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomUseCase roomUseCase;

    @GetMapping("/{floor}")
    public ResponseData<List<RoomRes>> roomList(
            @PathVariable("floor") Integer floor
    ){
        return roomUseCase.roomList(floor);
    }
}
