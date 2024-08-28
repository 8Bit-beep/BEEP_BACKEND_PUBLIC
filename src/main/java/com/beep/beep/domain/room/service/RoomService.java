package com.beep.beep.domain.room.service;

import com.beep.beep.domain.room.domain.repository.RoomRepository;
import com.beep.beep.domain.room.exception.RoomNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public void existsByCode(String code) {
        if(!roomRepository.existsById(code))
            throw RoomNotFoundException.EXCEPTION;
    }
}
