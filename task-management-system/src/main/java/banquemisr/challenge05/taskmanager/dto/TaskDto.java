package banquemisr.challenge05.taskmanager.dto;

import banquemisr.challenge05.taskmanager.util.LocalDateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class TaskDto {

    private Long id;

    @NotBlank(message = "Title is mandatory")
    @Size(max = 255, message = "Title must be less than 255 characters")
    private String title;

    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;

    @NotBlank(message = "Status is mandatory")
    @Pattern(regexp = "TODO|IN_PROGRESS|DONE", message = "Status must be either TODO, IN_PROGRESS, or DONE")
    private String status;

    @NotBlank(message = "Priority is mandatory")
    @Pattern(regexp = "LOW|MEDIUM|HIGH", message = "Priority must be either LOW, MEDIUM, or HIGH")
    private String priority;

    @NotNull(message = "Due date is mandatory")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    @JsonIgnore
    private UserDto userDto;

}
