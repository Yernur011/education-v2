package com.rdlab.education.controller.notification;


import com.rdlab.education.domain.entity.edu.Notification;
import com.rdlab.education.service.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import static com.rdlab.education.utils.codes.ProductCode.NOTIFICATION_URI;
import static com.rdlab.education.utils.codes.ProductCode.V1_URI;

@RestController
@RequestMapping(V1_URI + NOTIFICATION_URI)
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping
    public Page<Notification> getNotifications(
            @RequestParam(name = "is_read", required = false) Boolean isRead,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        if (isRead == null) {
            return notificationService.getNotifications(page, size);
        }
        return notificationService.getNotificationsByIsRead(isRead, page, size);
    }

    @PutMapping("{id}")
    public void updateNotification(@PathVariable Long id) {
        notificationService.readNotification(id);
    }

}
