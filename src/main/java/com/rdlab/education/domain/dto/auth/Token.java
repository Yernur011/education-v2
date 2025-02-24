package com.rdlab.education.domain.dto.auth;

public record Token (
    String token,
    String type,
    String createdAt,
    String expiredAt){
}
