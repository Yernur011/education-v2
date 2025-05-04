package com.rdlab.education.domain.clients;

import com.rdlab.education.domain.dto.integration.zoom.ZoomMeetingCreatedResponseDto;
import com.rdlab.education.domain.dto.integration.zoom.ZoomMeetingRequestDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ZoomMeetingClient {

    private final RestClient restClient;

    public ZoomMeetingClient(RestClient zoomMeetingRestClient) {
        this.restClient = zoomMeetingRestClient;
    }

    public ZoomMeetingCreatedResponseDto createMeeting(String accessToken, ZoomMeetingRequestDto request) {
        return restClient.post()
                .uri("/users/me/meetings")
                .header("Authorization", "Bearer " + accessToken)
                .body(request)
                .retrieve()
                .body(ZoomMeetingCreatedResponseDto.class);
    }
}