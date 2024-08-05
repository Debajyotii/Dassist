package banquemisr.challenge05.taskmanager.util;

import banquemisr.challenge05.taskmanager.dto.TaskDto;
import banquemisr.challenge05.taskmanager.dto.UserDetailsDto;
import banquemisr.challenge05.taskmanager.dto.UserDto;
import banquemisr.challenge05.taskmanager.model.Task;
import banquemisr.challenge05.taskmanager.model.User;
import banquemisr.challenge05.taskmanager.model.UserDetails;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public DtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDto toDTO(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public TaskDto toDTO(Task task) {
        return modelMapper.map(task, TaskDto.class);
    }

    public UserDetailsDto toDTO(UserDetails userDetails) {
        return modelMapper.map(userDetails, UserDetailsDto.class);
    }

    public User toEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public Task toEntity(TaskDto taskDto) {
        return modelMapper.map(taskDto, Task.class);
    }

    public UserDetails toEntity(UserDetailsDto userDetailsDto) {
        return modelMapper.map(userDetailsDto, UserDetails.class);
    }
}
