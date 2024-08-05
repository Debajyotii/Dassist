package banquemisr.challenge05.taskmanager.emailscheduler;

import banquemisr.challenge05.taskmanager.model.Task;
import banquemisr.challenge05.taskmanager.repository.TaskRepository;
import banquemisr.challenge05.taskmanager.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/*
This Feature is disabled now, as this need SMTP Credentials
*/
@Component
@RequiredArgsConstructor
@Slf4j
public class AppScheduler {
    private TaskRepository taskRepository;
    private EmailService emailService;

    @Scheduled(cron = "0 0 8 * * ?") // Runs every day at 8 AM
    public void sendDeadlineNotifications() {
        try {
            LocalDate today = LocalDate.now();
            // Notify 1 day before deadline
            LocalDate upcomingDeadline = today.plusDays(1);
            List<Task> tasks = taskRepository.findByDueDate(upcomingDeadline);
            for (Task task : tasks) {
                var notification = emailService.prepareMail(task);
                notification.ifPresent(value -> emailService.sendEmail(value));
            }
        } catch (
                Exception e) {
            log.error("Unable to run scheduler", e);
        }
    }
}
