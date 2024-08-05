package banquemisr.challenge05.taskmanager.service;


import banquemisr.challenge05.taskmanager.dto.TaskDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface TaskService {

    TaskDto createTask(TaskDto task);

    TaskDto getTaskById(Long id);

    TaskDto updateTask(Long id, TaskDto taskDtoDetails);

    Long deleteTask(Long id);

    Page<TaskDto> getAllTasks(Pageable pageable);

    List<TaskDto> searchTasks(TaskDto taskDtoSearchDetails);

    void deleteAllTask();

}
