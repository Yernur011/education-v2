package com.rdlab.education.domain.repository;

import com.rdlab.education.domain.entity.edu.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
