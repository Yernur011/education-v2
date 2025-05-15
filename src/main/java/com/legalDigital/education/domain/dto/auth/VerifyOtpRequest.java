package com.legalDigital.education.domain.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record VerifyOtpRequest(@NotBlank(message = "Имя пользователя не может быть пустыми")
                               @Email(message = "Укажте корректную почту")
                               String username,

                               @NotBlank(message = "Код подтверждения не должен быть пустым")
                               @Size(min = 6, max = 6, message ="Код подтверждения должен состоять из 8 символов")
                               String otp) {
}
