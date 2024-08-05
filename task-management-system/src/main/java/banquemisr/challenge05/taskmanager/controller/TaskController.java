package banquemisr.challenge05.taskmanager.controller;

import banquemisr.challenge05.taskmanager.dto.TaskDto;
import banquemisr.challenge05.taskmanager.service.TaskService;
import banquemisr.challenge05.taskmanager.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static banquemisr.challenge05.taskmanager.util.ResponseCreator.createResponse;

@RestController
@RequestMapping("/taskmanager/task")
@AllArgsConstructor
@Tag(name = "Task", description = "APIs for task management")
public class TaskController {

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    private final TaskService taskService;
    private final UsersService usersService;

    @Operation(
            summary = "Fetch all tasks",
            description = "Retrieve all tasks with pagination",
            tags = {"Task"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tasks retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public Page<TaskDto> getAllTasks(
            @Parameter(description = "Page number for pagination", example = "0") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size for pagination", example = "4") @RequestParam(defaultValue = "4") int size) {
        logger.info("Fetching all tasks with pagination");
        Pageable pageable = PageRequest.of(page, size);
        return taskService.getAllTasks(pageable);
    }

    @Operation(
            summary = "Create a new task",
            description = "Create a new task with the provided details",
            tags = {"Task"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<Map<String, Object>> createTask(
            @Valid @RequestBody TaskDto taskDto,
            @Parameter(hidden = true) Authentication authentication) {
        logger.info("Creating new task with description: {}", taskDto.getDescription());
        taskDto.setUserDto(usersService.retrieveCurrentAuthUser(authentication));
        return new ResponseEntity<>(createResponse("created", taskService.createTask(taskDto)), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Fetch a task by ID",
            description = "Retrieve a specific task by its ID",
            tags = {"Task"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Task not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getTaskById(
            @Parameter(description = "ID of the task to retrieve", example = "1") @PathVariable Long id) {
        logger.info("Fetching task with ID: {}", id);
        return new ResponseEntity<>(createResponse("retrieved", taskService.getTaskById(id)), HttpStatus.OK);
    }

    @Operation(
            summary = "Update a task",
            description = "Update an existing task by its ID",
            tags = {"Task"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task updated successfully"),
            @ApiResponse(responseCode = "404", description = "Task not found"),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateTask(
            @Parameter(description = "ID of the task to update", example = "1") @PathVariable Long id,
            @RequestBody TaskDto taskDto) {
        logger.info("Updating task with ID: {}", id);
        return new ResponseEntity<>(createResponse("updated", taskService.updateTask(id, taskDto)), HttpStatus.OK);
    }

    @Operation(
            summary = "Delete a task",
            description = "Delete a specific task by its ID",
            tags = {"Task"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Task not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteTask(
            @Parameter(description = "ID of the task to delete", example = "1") @PathVariable Long id) {
        logger.info("Deleting task with ID: {}", id);
        return new ResponseEntity<>(createResponse("Deleted", taskService.deleteTask(id)), HttpStatus.OK);
    }

    @Operation(
            summary = "Delete all tasks",
            description = "Delete all tasks in the system",
            tags = {"Task"}
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All tasks deleted successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Map<String, Object>> deleteAllTask() {
        logger.info("Deleting All Task");
        taskService.deleteAllTask();
        return new ResponseEntity<>(createResponse("Deleted All"), HttpStatus.OK);

    }

    @Operation(
            summary = "Search tasks",
            description = "Search tasks with specified filters",
            tags = {"Task"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tasks retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid filter criteria"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchTasks(
            @RequestBody TaskDto taskDto) {
        logger.info("Searching tasks with Filter: {}", taskDto);
        return new ResponseEntity<>(createResponse("Success", taskService.searchTasks(taskDto)), HttpStatus.OK);
    }


}
