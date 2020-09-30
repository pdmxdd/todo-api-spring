package org.launchcode.todo.Models;

public class TodoDto {

    private String text;

    public TodoDto() {}

    public TodoDto(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
}
