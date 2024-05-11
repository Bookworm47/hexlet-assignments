package exercise.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;

    Task task;

    @BeforeEach
    public void createInstance() {
        task = generateTask();
    }

    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    private Task generateTask() {
        return Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                .supply(Select.field(Task::getDescription), () -> faker.lorem().paragraph())
                .create();
    }

    // BEGIN
    @Test
    public void testCreate() throws Exception {
        var taskJson = om.writeValueAsString(task);
        var putResult = mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString((task))))
                .andExpect(status().isCreated())
                .andReturn();
        var putResultTask = putResult.getResponse().getContentAsString();

        assertThatJson(putResultTask).and(
                a -> a.node("title").isEqualTo(task.getTitle()),
                a -> a.node("description").isEqualTo(task.getDescription())
        );
    }

    @Test
    public void testShow() throws Exception {
        taskRepository.save(task);
        long fakeId = 2;
        var succsessResult = mockMvc.perform(get("/tasks/" + task.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        var body = succsessResult.getResponse().getContentAsString();
        assertThatJson(body).isEqualTo(om.writeValueAsString(task));

        var exeptionResult = mockMvc.perform(get("/tasks/" + fakeId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
        var exeption = exeptionResult.getResponse().getContentAsString();
        assertThat(exeption).isEqualTo("Task with id " + fakeId + " not found");

    }

    @Test
    public void testUpdate() throws Exception {
        taskRepository.save(task);

        var data = new HashMap<>();
        data.put("title", "Study");

        var request = put("/tasks/" + task.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(data));

        mockMvc.perform(request)
                .andExpect(status().isOk());
        task = taskRepository.findById(task.getId()).get();
        assertThat(task.getTitle()).isEqualTo("Study");
    }

    @Test
    public void testDelete() throws Exception {
        taskRepository.save(task);

        var result = mockMvc.perform(delete("/tasks/" + task.getId()))
                .andReturn();

        boolean deleteTask = taskRepository.findById(task.getId()).isEmpty();
        assertThat(deleteTask).isEqualTo(true);
    }
    // END
}
