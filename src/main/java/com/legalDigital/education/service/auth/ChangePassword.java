package com.legalDigital.education.service.auth;

import com.legalDigital.education.domain.entity.auth.ChangePasswordRequest;
import com.legalDigital.education.domain.entity.auth.Users;
import com.legalDigital.education.domain.exceptions.ApiException;
import com.legalDigital.education.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChangePassword {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void changePassword(ChangePasswordRequest request) {
        if (!request.newPassword().equals(request.newPasswordConfirmation())) {
            throw new ApiException("Новые пароли не совпадают!");
        }
        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user = principal.user();

        if (!passwordEncoder.matches(request.oldPassword(), user.getPassword())) {
            throw new ApiException("Старый пароль не совпадает!");
        }
        userRepository.findByUsername(user.getUsername())
                .map(users -> {
                    users.setPassword(passwordEncoder.encode(request.newPassword()));
                    return users;
                })
                .map(userRepository::save)
                .ifPresent(users -> log.info("Пароль успешно изменен!"));
    }
}
