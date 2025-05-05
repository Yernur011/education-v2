package com.rdlab.education.domain.repository;

import com.rdlab.education.domain.entity.edu.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Page<Notification> findByUsername(String username, Pageable pageable);
    @Query(value = "SELECT n from Notification n " +
            "where n.username = :username " +
            "AND n.isRead = :read")

    Page<Notification> findByUsernameAndRead(@Param("username") String username, @Param("read") boolean read, Pageable pageable);
}
