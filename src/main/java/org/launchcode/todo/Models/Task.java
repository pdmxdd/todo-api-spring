package org.launchcode.todo.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id;

    String text;

    @ManyToOne
    @JoinColumn(name = "todo_item_id", nullable = false)
    TodoItem todoItem;

    public Task() {}

    public Task(String text) {
        this.text = text;
    }

    public int getId() {
        return this.id;
    }

    public String getText() {
        return this.text;
    }

    public TodoItem getTodoItem() {
        return this.todoItem;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTodoItem(TodoItem todoItem) {
        this.todoItem = todoItem;
    }
    
}
