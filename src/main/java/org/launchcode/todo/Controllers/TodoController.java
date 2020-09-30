package org.launchcode.todo.Controllers;

import java.util.List;

import org.launchcode.todo.Models.TodoDto;
import org.launchcode.todo.Models.TodoItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/todos")
public class TodoController {

    @GetMapping
    public List<TodoItem> getTodos() {
        return TodoItem.findAllItems();
    }

    @PostMapping
    public TodoItem postTodo(@RequestBody TodoDto todoDto) {
        return TodoItem.createItem(todoDto.getText());
    }

    @PatchMapping(value = "/{id}")
    public TodoItem patchTodo(@PathVariable int id) {
        TodoItem theItem = TodoItem.findItem(id);
        theItem.markAsComplete();
        return theItem;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteTodo(@PathVariable int id) {
        boolean deleted = TodoItem.deleteItem(id);
        if(deleted) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    
}
