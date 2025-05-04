package com.rdlab.education.domain.dto.integration.zoom;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MeetingReqDto {

    private String topic;
    private Long category;
    @JsonProperty("start_time")
    private String startTime; // 2025-04-22T10:17:00Z
    private int duration;

}
