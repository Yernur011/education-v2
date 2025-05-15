package com.legalDigital.education.domain.entity.auth;

public record ChangePasswordRequest(String oldPassword,
                                    String newPassword,
                                    String newPasswordConfirmation) {
}
