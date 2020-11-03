package org.launchcode.todo.Models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * TodoItem Interface
 * use this interface to guide the implementation of the TodoItem class
 */
interface ITodoItem {
  int getId();
  String getText();
  boolean getCompleted();
  TodoItem markAsComplete();
}

/**
 * TodoItem Class
 * 
 * <ul>
 *  <li>holds an internal store managing TodoItem instances</li>
 *  <li>exposes static methods for interacting with items in the store</li>
 *  <li>instances must implement the <code>ITodoItem</code> interface</li>
 * </ul>
 */
@Entity
public class TodoItem implements ITodoItem {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private int id;
  private String text;
  private boolean completed;

  @OneToMany(mappedBy = "todoItem", cascade = CascadeType.ALL)
  List<Task> tasks = new ArrayList<>();

  public int getId() {
    return this.id;
  }

  public String getText() {
    return this.text;
  }

  public boolean getCompleted() {
    return this.completed;
  }

  public List<Task> getTasks() {
    return this.tasks;
  }

  public void setTasks(List<Task> tasks) {
    this.tasks = tasks;
  }

  public void addTasks(Task task) {
    this.tasks.add(task);
  }

  @Override
  public TodoItem markAsComplete() {
    this.completed = true;
    return this;
  }

  private TodoItem(String text) {
    this.text = text;
    this.completed = false;
  }

  public TodoItem() {}

  public static TodoItem createItem(String text) {
    TodoItem todoItem = new TodoItem(text);
    
    return todoItem;
  }

}