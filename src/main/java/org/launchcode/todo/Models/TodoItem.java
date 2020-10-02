package org.launchcode.todo.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
  // keeps track of the item Ids to ensure they are unique
  // private static int nextId = 0;
  // uses a HashMap for fast lookups 
  // private static HashMap<Integer, TodoItem> store = new HashMap();

  // TODO: implement the fields to hold the instance state (id, text, completed)
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private int id;
  private String text;
  private boolean completed;

  // TODO: implement the interface methods
  public int getId() {
    return this.id;
  }

  public String getText() {
    return this.text;
  }

  public boolean getCompleted() {
    return this.completed;
  }

  @Override
  public TodoItem markAsComplete() {
    this.completed = true;
    return this;
  }

  // force use of createItem method rather than direct instantiation
  private TodoItem(String text) {
    // TODO: assign the id and text values
    this.text = text;
    this.completed = false; // initialize with incomplete status
  }

  public TodoItem() {}

  public static TodoItem createItem(String text) {
    // TODO: instantiate the TodoItem instance using the private constructor
    // int id = ++nextId; // auto-increment the Id to ensure unique
    TodoItem todoItem = new TodoItem(text);
    
    // add it to the store
    // store.put(id, todoItem);
    
    // return the newly created item
    return todoItem;
  }

  // public static TodoItem findItem(int id) {
  //   // TODO: implement the method for finding an item in the store
  //   // use the signature to guide your logic
  //   for(int todoId : store.keySet()) {
  //     if(id == todoId) {
  //       return store.get(id);
  //     }

  //   }
  //   return null;
  // }

  // public static List<TodoItem> findAllItems() {
  //   // TODO: implement the method for getting all the items in the store
  //   // you will need to convert the HashMap to a List of TodoItem objects so that it can be sent as a JSON array
  //   return new ArrayList<TodoItem>(store.values());
  // }

  // public static boolean deleteItem(int id) {
  //   // TODO: implement the method for deleting an item in the store
  //   // use the signature to guide your logic
  //   for(int todoId : store.keySet()) {
  //     if(id == todoId) {
  //       store.remove(id);
  //       return true;
  //     }
  //   }
  //   return false;
  // }

}