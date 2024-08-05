package banquemisr.challenge05.taskmanager.service.impl;

import banquemisr.challenge05.taskmanager.dto.NotificationDto;
import banquemisr.challenge05.taskmanager.model.Task;
import banquemisr.challenge05.taskmanager.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
class EmailServiceImpl implements EmailService {
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(NotificationDto notificationDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(notificationDto.getTo());
        message.setSubject(notificationDto.getSubject());
        message.setText(notificationDto.getText());
        mailSender.send(message);
    }

    @Override
    public Optional<NotificationDto> prepareMail(Task task) {

        try {
            String email = task.getUser().getUserDetails().getEmail();
            String subject = "Task Deadline Reminder";
            String text = "Dear user, your task \"" + task.getTitle() + "\" is due on " + task.getDueDate() + ". Please complete it before the deadline.";
            return Optional.of(new NotificationDto(email, subject, text));
        } catch (Exception e) {
            log.error("Unable to send mail for task {}", task);
            return Optional.empty();
        }
    }
}
