package com.rdlab.education.domain.dto.material;

import java.time.LocalDateTime;

public record ZoomDto(Long id, String title, String status, LocalDateTime plannedDateTime) {}
