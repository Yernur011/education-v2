package com.rdlab.education.service.notification;

import com.rdlab.education.domain.entity.edu.Notification;
import com.rdlab.education.domain.exceptions.NotFoundException;
import com.rdlab.education.domain.repository.NotificationRepository;
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

    public Page<Notification> getNotifications(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }

    public Notification getNotification(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Notification not found"));
    }

    public void createNotification(Notification notification) {
//        var saved = repository.save(notification);
        accountService.notifyAllUsers(notification);
    }

    public void createNotification(Long topicId, Notification notification) {
//        var saved = repository.save(notification);
        accountService.notifyAllUsersWithTopics(topicId, notification);
    }

    public void readNotification(Long id) {
        var notification = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Notification not found"));
        notification.setRead(true);
        repository.save(notification);
    }
}
