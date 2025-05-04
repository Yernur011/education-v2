package com.rdlab.education.service.crud.impl;

import com.rdlab.education.domain.clients.ZoomMeetingClient;
import com.rdlab.education.domain.clients.ZoomTokenClient;
import com.rdlab.education.domain.dto.integration.zoom.MeetingReqDto;
import com.rdlab.education.domain.dto.integration.zoom.ZoomMeetingCreatedResponseDto;
import com.rdlab.education.domain.dto.integration.zoom.ZoomMeetingRequestDto;
import com.rdlab.education.domain.entity.edu.Notification;
import com.rdlab.education.domain.entity.edu.Zoom;
import com.rdlab.education.domain.enums.NotificationEntityType;
import com.rdlab.education.domain.exceptions.NotFoundException;
import com.rdlab.education.domain.repository.edu.ZoomRepository;
import com.rdlab.education.service.crud.ZoomService;
import com.rdlab.education.service.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class ZoomServiceImpl implements ZoomService {

    private final ZoomRepository zoomRepository;
    private final ZoomTokenClient zoomTokenClient;
    private final ZoomMeetingClient zoomMeetingClient;
    private final NotificationService notificationService;


    public Page<Zoom> getAll(int page, int size) {
        return zoomRepository.findAll(PageRequest.of(page, size));
    }

    public Zoom getById(Long id) throws NotFoundException {
        return zoomRepository.findByZoomMeetingJsonId(id.toString())
                .orElseThrow(() -> new NotFoundException("Zoom Meeting with id " + id + " not found"));
    }

    public Zoom save(Zoom zoom) {
        return zoomRepository.save(zoom);
    }

    public void delete(Long id) {
        zoomRepository.deleteById(id);
    }

    public ZoomMeetingCreatedResponseDto createMeeting(MeetingReqDto dto) {
        var token = zoomTokenClient.getToken("account_credentials", "g5LrouiASraTs9HJqgCKZQ");
        ZoomMeetingRequestDto request = ZoomMeetingRequestDto.builder()
                .startTime(dto.getStartTime())
                .topic(dto.getTopic())
                .duration(dto.getDuration())
                .type(2)
                .timezone("Asia/Almaty")
                .settings(ZoomMeetingRequestDto.Settings.builder()
                        .hostVideo(true)
                        .joinBeforeHost(true)
                        .participantVideo(true)
                        .build())
                .build();
        var response = zoomMeetingClient.createMeeting(token, request);
        var zoom = new Zoom(null, response.getId(), dto.getTopic(), "CREATED",
                ZonedDateTime.parse(dto.getStartTime()).toLocalDateTime(), response);
        zoomRepository.save(zoom);

        notificationService.createNotification(
                dto.getCategory(),
                Notification.builder()
                        .title("ZOOM Конференция по вашему интересу")
                        .description("Тема: " + dto.getTopic() + "\nВремя: " + dto.getStartTime())
                        .entityId(response.getId())
                        .entity(NotificationEntityType.ZOOM)
                        .startTime(response.getStartTime())
                        .build());
        return response;
    }
}
