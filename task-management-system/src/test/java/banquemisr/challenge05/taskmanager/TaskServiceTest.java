package banquemisr.challenge05.taskmanager;

import banquemisr.challenge05.taskmanager.dto.TaskDto;
import banquemisr.challenge05.taskmanager.dto.UserDto;
import banquemisr.challenge05.taskmanager.service.TaskService;
import banquemisr.challenge05.taskmanager.service.UsersService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TaskServiceTest {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UsersService usersService;

    private TaskDto taskDto;

    @BeforeEach
    void setUp() {
        taskDto = new TaskDto();
        taskDto.setTitle("mail");
        taskDto.setDescription("Need to work");
        taskDto.setPriority("1");
        taskDto.setStatus("TODO");
        taskDto.setDueDate(LocalDate.now());
        UserDto userDto = new UserDto();
        userDto.setPassword("demo");
        userDto.setUsername("test");
        userDto.setRole("xyz");
        taskDto.setUserDto(usersService.createUser(userDto));
    }

    @AfterEach
    void clearUp() {
        taskService.deleteAllTask();
        usersService.deleteAllUser();
    }

    @Test
    void shouldCreateAndRetrieveTask() {
        TaskDto savedTask = taskService.createTask(taskDto);
        TaskDto retrievedTask = taskService.getTaskById(savedTask.getId());
        assertThat(retrievedTask).isNotNull();
        assertThat(retrievedTask.getTitle()).isEqualTo("mail");
    }

    @Test
    void shouldUpdateTask() {
        TaskDto savedTask = taskService.createTask(taskDto);
        taskDto.setTitle("Updated Task");
        taskService.updateTask(savedTask.getId(), taskDto);
        TaskDto updatedTask = taskService.getTaskById(savedTask.getId());
        assertThat(updatedTask.getTitle()).isEqualTo("Updated Task");
    }

    @Test
    void shouldDeleteTask() {
        TaskDto savedTask = taskService.createTask(taskDto);
        assertEquals(taskService.deleteTask(savedTask.getId()), savedTask.getId());
    }
}
