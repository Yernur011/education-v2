package com.rdlab.education.domain.clients;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Component
public class ZoomTokenClient {

    private final RestClient restClient;

    public ZoomTokenClient(RestClient zoomTokenRestClient) {
        this.restClient = zoomTokenRestClient;
    }

    public String getToken(String grantType, String accountId) {
        String uri = UriComponentsBuilder
                .fromPath("/oauth/token")
                .queryParam("grant_type", grantType)
                .queryParam("account_id", accountId)
                .build()
                .toUriString();

        var body = restClient.post()
                .uri(uri)
                .retrieve()
                .body(Map.class);
        if (body != null && body.get("access_token") != null) {
            return body.get("access_token").toString();
        }
        return null;
    }

}
