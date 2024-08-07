package com.beep.beep.domain.email.presentation.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

public record EmailSendingRequest(@NotBlank(message = "이메일 입력은 필수입니다.")
                                  @Email(message = "이메일 형식에 맞게 입력해 주세요.")
                                  String email) {
}
