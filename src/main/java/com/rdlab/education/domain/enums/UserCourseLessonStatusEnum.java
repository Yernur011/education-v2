package com.rdlab.education.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserCourseLessonStatusEnum {
    ACTIVE("ACTIVE"),
    STARTED("STARTED"),
    COMPLETED("COMPLETED");

    private final String status;


}
