package banquemisr.challenge05.taskmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotificationDto {
    String to;
    String subject;
    String text;
}
