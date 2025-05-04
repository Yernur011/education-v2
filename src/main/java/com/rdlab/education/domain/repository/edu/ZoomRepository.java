package com.rdlab.education.domain.repository.edu;

import com.rdlab.education.domain.entity.edu.Zoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ZoomRepository extends JpaRepository<Zoom, Long> {

    @Query(value = "SELECT * FROM zoom z WHERE z.response_dto ->> 'id' = :meetingId", nativeQuery = true)
    Optional<Zoom> findByZoomMeetingJsonId(@Param("meetingId") String meetingId);

}
