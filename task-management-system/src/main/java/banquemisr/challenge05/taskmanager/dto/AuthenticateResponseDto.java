package banquemisr.challenge05.taskmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticateResponseDto {
    private String accessToken;
    private String refreshToken;
}
