package banquemisr.challenge05.taskmanager.service;


import banquemisr.challenge05.taskmanager.dto.NotificationDto;
import banquemisr.challenge05.taskmanager.model.Task;

import java.util.Optional;

public interface EmailService {
    void sendEmail(NotificationDto notificationDto);

    Optional<NotificationDto> prepareMail(Task task);
}
