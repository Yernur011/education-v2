package com.rdlab.education.domain.repository;

import com.rdlab.education.domain.entity.edu.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Page<Notification> findByUsername(String username, Pageable pageable);

    Page<Notification> findByUsernameAndRead(String username, boolean read, Pageable pageable);
}
