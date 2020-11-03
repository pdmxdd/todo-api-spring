package org.launchcode.todo.Models;

public class TaskDto {
    String text;

    public TaskDto() {}

    public TaskDto(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Task toTask() {
        return new Task(this.text);
    }

    public static TaskDto buildDtoFromTask(Task task) {
        return new TaskDto(task.getText());
    }
}
