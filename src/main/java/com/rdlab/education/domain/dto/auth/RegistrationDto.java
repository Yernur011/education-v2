package com.rdlab.education.domain.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record RegistrationDto(@NotBlank(message = "Имя пользователя не может быть пустыми")
                              @Email(message = "Укажте корректную почту")
                              String username,

                              @Size(min = 8, message = "Пароль должен быть не менее 8 символов")
                              @NotBlank(message = "Пароль должен не может быть пустым")
                              String password,

                              @NotBlank(message = "Имя пользователя не может быть пустыми")
                              String name,
                              List<Long> categoryIdList
) {
}
