package com.rdlab.education.service.notification;

import com.rdlab.education.domain.entity.edu.Notification;
import com.rdlab.education.domain.exceptions.NotFoundException;
import com.rdlab.education.domain.repository.NotificationRepository;
import com.rdlab.education.service.auth.UserService;
import com.rdlab.education.service.crud.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository repository;
    private final AccountService accountService;
    private final UserService userService;

    public Page<Notification> getNotifications(int page, int size) {
        return repository.findByUsername(userService.getCurrentUser().getName(), PageRequest.of(page, size));
    }

    public Page<Notification> getNotificationsByIsRead(boolean isRead, int page, int size) {
        return repository.findByUsernameAndRead(userService.getCurrentUser().getName(), isRead, PageRequest.of(page, size));
    }

    public Notification getNotification(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Notification not found"));
    }

    public void createNotification(Notification notification) {
        repository.saveAll(
                accountService.notifyAllUsers(notification));

    }

    public void createNotification(Long topicId, Notification notification) {
        repository.saveAll(
                accountService.notifyAllUsersWithTopics(topicId, notification));
    }

    public void readNotification(Long id) {
        var notification = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Notification not found"));
        notification.setRead(true);
        repository.save(notification);
    }
}
