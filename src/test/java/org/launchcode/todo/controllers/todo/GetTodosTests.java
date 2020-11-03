package org.launchcode.todo.controllers.todo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.launchcode.todo.IntegrationTestConfig;
import org.launchcode.todo.data.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

@IntegrationTestConfig
public class GetTodosTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TodoRepository todoRepository;

    // @Test
    // @DisplayName(value = "stuff")
    // public void getTodos() throws Exception {

    // }
    
}
