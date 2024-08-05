package banquemisr.challenge05.taskmanager;

import banquemisr.challenge05.taskmanager.service.TaskService;
import banquemisr.challenge05.taskmanager.service.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {

    private final static String url = "/taskmanager/task";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private FilterChainProxy filterChain;
    @MockBean
    private TaskService taskService;
    @MockBean
    private UsersService usersService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void shouldReturnAllTasks() throws Exception {
        mockMvc.perform(get(url))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void shouldReturnTaskById() throws Exception {
        mockMvc.perform(get(url + "/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void shouldCreateNewTask() throws Exception {
        String newTask = "{\"title\": \"New Task\", \"description\": \"Task description\", " +
                "\"status\": \"TODO\", \"priority\": \"HIGH\", \"dueDate\": \"2024-07-17\"}";

        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newTask))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldUpdateTask() throws Exception {
        String updatedTask = "{\"title\": \"New Task\", \"description\": \"Task description\", " +
                "\"status\": \"TODO\", \"priority\": \"1\", \"dueDate\": \"2024-07-17\"}";

        mockMvc.perform(put(url + "/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedTask))
                .andExpect(status().isOk());
    }

}
