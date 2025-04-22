package com.rdlab.education.domain.dto.integration.zoom;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ZoomWebhookRequest {
    private String event;
    private Map<String, String> payload = new HashMap<>();

    @JsonAnySetter
    public void addPayload(String key, Object value) {
        if ("payload".equals(key) && value instanceof Map<?, ?> map) {
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                if (entry.getKey() instanceof String && entry.getValue() instanceof String) {
                    payload.put((String) entry.getKey(), (String) entry.getValue());
                }
            }
        }
    }
}