package com.legalDigital.education.domain.enums;

import lombok.Getter;

@Getter
public enum ForumQuestionState {
    CREATED("Created"),
    APPROVED("Approved"),
    REVOKED("Revoked");
    private String state;
    ForumQuestionState(String state) {
        this.state = state;
    }
}
