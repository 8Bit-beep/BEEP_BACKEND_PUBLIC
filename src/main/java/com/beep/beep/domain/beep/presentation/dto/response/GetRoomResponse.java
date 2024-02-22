package com.beep.beep.domain.beep.presentation.dto.response;


import com.beep.beep.domain.beep.domain.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder @AllArgsConstructor
public class GetRoomResponse {

    private String room;

    public static GetRoomResponse of(Room room) {
        return GetRoomResponse.builder()
                .room(room.getName()).build();
    }

}
