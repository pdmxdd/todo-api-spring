package org.launchcode.todo.controllers.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.launchcode.todo.IntegrationTestConfig;
import org.launchcode.todo.Models.Task;
import org.launchcode.todo.Models.TodoItem;
import org.launchcode.todo.data.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@IntegrationTestConfig
public class GetTasksTests {
    
    @Autowired
    MockMvc mockMvc;

    @Autowired
    TodoRepository todoRepository;

    @BeforeEach
    public void clearDatabase() {
        todoRepository.deleteAll();
    }

    @Test
    @DisplayName(value = "GET /todos/{id}/tasks Not Found")
    public void getTodoTasksNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/todos/-1/tasks"))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DisplayName(value = "GET /todos/{id}/tasks empty list")
    public void getTodoTasksEmpty() throws Exception {
        TodoItem testTodoItem = todoRepository.save(TodoItem.createItem("test item"));
        mockMvc.perform(MockMvcRequestBuilders.get("/todos/" + testTodoItem.getId() + "/tasks"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0));
    }

    @Test
    @DisplayName(value = "GET /todos/{id}/tasks populated")
    public void getTodoTasksPopuated() throws Exception {
        TodoItem testTodoItem = todoRepository.save(TodoItem.createItem("test item"));
        Task testTask = new Task("test item subtask 1");
        testTask.setTodoItem(testTodoItem);
        System.out.println(testTask.getTodoItem());
        System.out.println(testTask.getText());
        testTodoItem.addTask(testTask);
        todoRepository.save(testTodoItem);
        mockMvc.perform(MockMvcRequestBuilders.get("/todos/" + testTodoItem.getId() + "/tasks"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].text").value(testTask.getText()));
    }

}
