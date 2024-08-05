package banquemisr.challenge05.taskmanager.dto;

import banquemisr.challenge05.taskmanager.util.Base64Deserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserDto {

    private Long id;

    @NotBlank(message = "Username is mandatory")
    private String username;

    @NotBlank(message = "Password is mandatory")
    @JsonDeserialize(using = Base64Deserializer.class)
    private String password;

    @NotNull(message = "Role is mandatory")
    @Pattern(regexp = "ADMIN|NORMAL", message = "Status must be either ADMIN or NORMAL")
    private String role;
}
