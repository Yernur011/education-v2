package com.rdlab.education.controller.integration.zoom;

import com.rdlab.education.domain.dto.integration.zoom.ZoomWebhookRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@RestController
@RequestMapping("/webhook/zoom")
@Slf4j
public class ZoomWebhookController {

    @Value("${zoom.secret-token}")
    private String zoomSecret;

    @PostMapping
    public ResponseEntity<?> handleWebhook(@RequestBody ZoomWebhookRequest body,
                                           @RequestHeader(value = "Authorization", required = false) String authHeader) {

        if (body.getEvent().equals("endpoint.url_validation")) {
            String encryptedToken = hmacSha256(body.getPayload().get("plainToken"), zoomSecret);
            return ResponseEntity.ok(Map.of(
                    "plainToken", body.getPayload().get("plainToken"),
                    "encryptedToken", encryptedToken,
                    "received", true
            ));
        }

        if (authHeader == null || !authHeader.equals(zoomSecret)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Unauthorized"));
        }

        log.info("Zoom Webhook Event: {}", body.getEvent());

        switch (body.getEvent()) {
            case "meeting.started" -> {
                // логика при начале встречи
            }
            case "meeting.ended" -> {
                // логика при завершении встречи
            }
            case "recording.completed" -> {
                // логика для скачивания записи
            }
        }

        return ResponseEntity.ok(Map.of("received", true));
    }

    private String hmacSha256(String data, String secret) {
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256_HMAC.init(secretKey);
            return Hex.encodeHexString(sha256_HMAC.doFinal(data.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            throw new RuntimeException("Failed to compute HMAC SHA256", e);
        }
    }
}