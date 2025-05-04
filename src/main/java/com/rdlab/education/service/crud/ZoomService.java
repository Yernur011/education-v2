package com.rdlab.education.service.crud;

import com.rdlab.education.domain.dto.integration.zoom.MeetingReqDto;
import com.rdlab.education.domain.dto.integration.zoom.ZoomMeetingCreatedResponseDto;
import com.rdlab.education.domain.entity.edu.Zoom;
import org.springframework.data.domain.Page;

public interface ZoomService {
    Page<Zoom> getAll(int page, int size);
    Zoom getById(Long id);
    Zoom save(Zoom zoom);
    void delete(Long id);
    ZoomMeetingCreatedResponseDto createMeeting(MeetingReqDto dto);
}
