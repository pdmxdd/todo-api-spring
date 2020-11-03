package org.launchcode.todo.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.launchcode.todo.Models.OutgoingTodoItem;
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
    public ResponseEntity getTodos() {
        // return TodoItem.findAllItems();
        List<TodoItem> todoItems = todoRepository.findAll();
        List<OutgoingTodoItem> outgoingItems = new ArrayList<>();
        for(TodoItem todoItem : todoItems) {
            outgoingItems.add(OutgoingTodoItem.outgoingTodoItemFromTodoItem(todoItem));
        }
        return ResponseEntity.status(200).body(outgoingItems);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getTodoById(@PathVariable int id) {
        // return TodoItem.findItem(id);
        Optional<TodoItem> todoItem = todoRepository.findById(id);
        if(todoItem.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        TodoItem item = todoItem.get();
        OutgoingTodoItem outgoingItem = OutgoingTodoItem.outgoingTodoItemFromTodoItem(item);
        return ResponseEntity.status(200).body(outgoingItem);
    }

    @PostMapping
    public ResponseEntity postTodo(@RequestBody TodoDto todoDto) {
        // return TodoItem.createItem(todoDto.getText());
        TodoItem todoItem = TodoItem.createItem(todoDto.getText());
        TodoItem updatedItem = todoRepository.save(todoItem);
        OutgoingTodoItem outgoingTodoItem = OutgoingTodoItem.outgoingTodoItemFromTodoItem(updatedItem);
        return ResponseEntity.status(201).body(outgoingTodoItem);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity patchTodo(@PathVariable int id) {
        // TodoItem theItem = TodoItem.findItem(id);
        // theItem.markAsComplete();
        // return theItem;
        Optional<TodoItem> todoItem = todoRepository.findById(id);
        if(todoItem.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        TodoItem updatedTodo = todoItem.get().markAsComplete();
        todoRepository.save(updatedTodo);
        OutgoingTodoItem outgoingTodoItem = OutgoingTodoItem.outgoingTodoItemFromTodoItem(updatedTodo);
        return ResponseEntity.status(200).body(outgoingTodoItem);
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
            return ResponseEntity.status(404).build();
        }
        todoRepository.delete(todoItem.get());
        return ResponseEntity.status(204).build();
    }
    
}
