package org.launchcode.todo.controllers.task;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.launchcode.todo.IntegrationTestConfig;
import org.launchcode.todo.Models.TaskDto;
import org.launchcode.todo.Models.TodoItem;
import org.launchcode.todo.data.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@IntegrationTestConfig
public class PostTasksTests {
    
    @Autowired
    MockMvc mockMvc;

    @Autowired
    TodoRepository todoRepository;

    @BeforeEach
    public void clearDatabase() {
        todoRepository.deleteAll();
    }

    @Test
    @DisplayName(value = "POST /todos/{id}/tasks Not Found")
    public void postTodoTasksNotFound() throws Exception {
        TaskDto testTaskDto = new TaskDto("subtask 1");
        String testTaskDtoJson = new ObjectMapper().writeValueAsString(testTaskDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/todos/-1/tasks").contentType(MediaType.APPLICATION_JSON).content(testTaskDtoJson))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DisplayName(value = "POST /todos/{id}/tasks")
    public void postTodoTasks() throws Exception {
        TodoItem testTodo = todoRepository.save(TodoItem.createItem("some todo text"));
        TaskDto testTaskDto = new TaskDto("subtask 1");
        String testTaskDtoJson = new ObjectMapper().writeValueAsString(testTaskDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/todos/" + testTodo.getId() + "/tasks").contentType(MediaType.APPLICATION_JSON).content(testTaskDtoJson))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
