package banquemisr.challenge05.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserDetailsDto {

    private Long id;

    @NotBlank(message = "Email is mandatory")
    private String email;
}
