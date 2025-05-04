package com.rdlab.education.controller.material;

import com.rdlab.education.domain.dto.integration.zoom.ZoomMeetingCreatedResponseDto;
import com.rdlab.education.service.crud.ZoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.rdlab.education.utils.codes.ProductCode.MEETINGS;
import static com.rdlab.education.utils.codes.ProductCode.V1_URI;

@RestController
@RequestMapping(V1_URI + MEETINGS)
@RequiredArgsConstructor
public class ZoomController {

    private final ZoomService zoomService;

    @GetMapping("/{id}")
    public ResponseEntity<ZoomMeetingCreatedResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(zoomService.getById(id).getResponseDto());
    }
}
