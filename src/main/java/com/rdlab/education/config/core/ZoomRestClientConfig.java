package com.rdlab.education.config.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ZoomRestClientConfig {

    @Bean
    public RestClient zoomTokenRestClient() {
        return RestClient.builder()
                .baseUrl("https://zoom.us")
                .defaultHeader("Authorization", "Basic eFREN3VJN1dTOVNYQkxpYkxReHJ1Zzp1SFVpa1p1Rmo2UmFjWEVMQ01Qbm10VnQyVTF3RDRITA==")
                .build();
    }

    @Bean
    public RestClient zoomMeetingRestClient() {
        return RestClient.builder()
                .baseUrl("https://api.zoom.us/v2")
                .defaultHeader("Content-Type", "application/json")
                .build();
    }
}