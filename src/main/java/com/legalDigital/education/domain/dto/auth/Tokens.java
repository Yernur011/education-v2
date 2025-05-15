package com.legalDigital.education.domain.dto.auth;

public record Tokens(String accessToken,
                     String accessTokenCreatedAt,
                     String accessTokenExpiresAt,
                     String refreshToken,
                     String refreshTokenCreatedAt,
                     String refreshTokenExpiresAt) {
}