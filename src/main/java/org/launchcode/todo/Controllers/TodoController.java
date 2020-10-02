package org.launchcode.todo.Controllers;

import java.util.List;
import java.util.Optional;

import org.launchcode.todo.Models.TodoDto;
import org.launchcode.todo.Models.TodoItem;
import org.launchcode.todo.data.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping
    public List<TodoItem> getTodos() {
        // return TodoItem.findAllItems();
        return todoRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TodoItem> getTodoById(@PathVariable int id) {
        // return TodoItem.findItem(id);
        Optional<TodoItem> todoItem = todoRepository.findById(id);
        if(todoItem.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<TodoItem>(todoRepository.findById(id).get(), HttpStatus.OK);
    }

    @PostMapping
    public TodoItem postTodo(@RequestBody TodoDto todoDto) {
        // return TodoItem.createItem(todoDto.getText());
        return todoRepository.save(TodoItem.createItem(todoDto.getText()));
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<TodoItem> patchTodo(@PathVariable int id) {
        // TodoItem theItem = TodoItem.findItem(id);
        // theItem.markAsComplete();
        // return theItem;
        Optional<TodoItem> todoItem = todoRepository.findById(id);
        if(todoItem.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<TodoItem>(todoRepository.save(todoItem.get().markAsComplete()), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteTodo(@PathVariable int id) {
        // boolean deleted = TodoItem.deleteItem(id);
        // if(deleted) {
        //     return new ResponseEntity(HttpStatus.NO_CONTENT);
        // }
        // return new ResponseEntity(HttpStatus.NOT_FOUND);
        Optional<TodoItem> todoItem = todoRepository.findById(id);
        if(todoItem.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        todoRepository.delete(todoItem.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
