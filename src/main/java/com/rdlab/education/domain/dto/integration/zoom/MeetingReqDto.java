package com.rdlab.education.domain.dto.integration.zoom;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class MeetingReqDto {

    private String topic;
    @JsonProperty("start_time")
    private ZonedDateTime startTime; // 2025-04-22T10:17:00Z
    private int duration;

}
