package com.rdlab.education.domain.dto.integration.zoom;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ZoomMeetingRequestDto {

    private String topic;
    private int type = 2;

    @JsonProperty("start_time")
    private String startTime;

    private int duration;
    private String timezone;

    private Settings settings;

    @Data
    @Builder
    public static class Settings {
        @JsonProperty("host_video")
        private boolean hostVideo;

        @JsonProperty("participant_video")
        private boolean participantVideo;

        @JsonProperty("join_before_host")
        private boolean joinBeforeHost;
    }
}