package com.rdlab.education.domain.entity.edu;

import com.rdlab.education.domain.dto.integration.zoom.ZoomMeetingCreatedResponseDto;
import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Zoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long meetingId;

    private String title;

    private String status;

    private LocalDateTime plannedDateTime;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private ZoomMeetingCreatedResponseDto responseDto;

}
