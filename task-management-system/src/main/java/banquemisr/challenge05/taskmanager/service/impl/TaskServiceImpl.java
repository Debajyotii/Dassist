package banquemisr.challenge05.taskmanager.service.impl;

import banquemisr.challenge05.taskmanager.dto.TaskDto;
import banquemisr.challenge05.taskmanager.exception.CustomException;
import banquemisr.challenge05.taskmanager.model.Task;
import banquemisr.challenge05.taskmanager.repository.TaskRepository;
import banquemisr.challenge05.taskmanager.service.TaskService;
import banquemisr.challenge05.taskmanager.util.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static banquemisr.challenge05.taskmanager.util.AppConstants.TASK_NOT_FOUND;

@Service
@RequiredArgsConstructor
class TaskServiceImpl implements TaskService {

    //@Autowired Since 4.3 Explicitly Auto wiring not required for CI
    private final TaskRepository taskRepository;
    private final DtoMapper dtoMapper;


    @Override
    public TaskDto createTask(TaskDto taskDto) {
        return dtoMapper.toDTO(taskRepository.save(dtoMapper.toEntity(taskDto)));
    }

    @Override
    public TaskDto getTaskById(Long id) {
        return dtoMapper.toDTO(taskRepository.findById(id).orElseThrow(() -> new CustomException(TASK_NOT_FOUND)));
    }

    @Override
    public TaskDto updateTask(Long id, TaskDto taskDtoDetails) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new CustomException(TASK_NOT_FOUND));
        if (taskDtoDetails.getTitle() != null)
            task.setTitle(taskDtoDetails.getTitle());
        if (taskDtoDetails.getDescription() != null)
            task.setDescription(taskDtoDetails.getDescription());
        if (taskDtoDetails.getStatus() != null)
            task.setStatus(taskDtoDetails.getStatus());
        if (taskDtoDetails.getPriority() != null)
            task.setPriority(taskDtoDetails.getPriority());
        if (taskDtoDetails.getDueDate() != null)
            task.setDueDate(taskDtoDetails.getDueDate());
        return dtoMapper.toDTO(taskRepository.save(task));
    }


    @Override
    public Long deleteTask(Long id) {
        taskRepository.deleteById((taskRepository.findById(id).orElseThrow(
                () -> new CustomException("Task not found")).getId()));
        return id;
    }

    @Override
    public Page<TaskDto> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable).map(dtoMapper::toDTO);
    }

    @Override
    public List<TaskDto> searchTasks(TaskDto taskDtoSearchDetails) {

        Task task = new Task();
        if (taskDtoSearchDetails.getTitle() != null)
            task.setTitle(taskDtoSearchDetails.getTitle());
        if (taskDtoSearchDetails.getDescription() != null)
            task.setDescription(taskDtoSearchDetails.getDescription());
        if (taskDtoSearchDetails.getStatus() != null)
            task.setStatus(taskDtoSearchDetails.getStatus());
        if (taskDtoSearchDetails.getPriority() != null)
            task.setPriority(taskDtoSearchDetails.getPriority());
        if (taskDtoSearchDetails.getDueDate() != null)
            task.setDueDate(taskDtoSearchDetails.getDueDate());

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Task> example = Example.of(task, matcher);
        return taskRepository.findAll(example).stream().map(dtoMapper::toDTO).toList();
    }


    @Override
    public void deleteAllTask() {
        taskRepository.deleteAll();
    }
}
