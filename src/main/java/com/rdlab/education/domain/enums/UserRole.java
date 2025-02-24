package com.rdlab.education.domain.enums;

public enum UserRole {
    ADMIN("admin"),
    MODERATOR("moderator"),
    USER("user"),
    GUEST("guest");

    private final String role;
    UserRole(String role) {
        this.role = role;
    }
    public String getRole() {
        return role;
    }
}
