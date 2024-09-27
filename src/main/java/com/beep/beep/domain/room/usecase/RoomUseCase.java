package com.beep.beep.domain.room.usecase;

import com.beep.beep.domain.room.exception.GetRoomListException;
import com.beep.beep.global.common.dto.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class RoomUseCase {

    public ResponseData<String> roomList(Integer floor){
        try {
            // classpath에서 파일 읽기
            ClassPathResource resource = new ClassPathResource(String.format("room%d.json", floor));
            byte[] jsonBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
            String jsonContent = new String(jsonBytes, StandardCharsets.UTF_8);

            // JSON 문자열을 ResponseEntity로 반환
            return ResponseData.ok("층별 실 정보 조회 성공", jsonContent);

        } catch (IOException e) {
            // 예외 발생 시 에러 메시지 반환
            throw GetRoomListException.EXCEPTION;
        }
    }

}
