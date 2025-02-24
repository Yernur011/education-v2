package com.rdlab.education.domain.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegistrationDto(@NotBlank(message = "Имя пользователя не может быть пустыми")
                              @Email(message = "Укажте корректную почту")
                              String username,
                              @Size(min = 8, message = "Пароль должен быть не менее 8 символов")
                              @NotBlank(message = "Имя пользователя не может быть пустыми")
                              String password) {
}
