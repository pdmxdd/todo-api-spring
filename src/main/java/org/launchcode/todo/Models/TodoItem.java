package org.launchcode.todo.Models;

import java.util.HashMap;

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
public class TodoItem implements ITodoItem {
  // keeps track of the item Ids to ensure they are unique
  private static int nextId = 0;
  // uses a HashMap for fast lookups 
  private static HashMap<Integer, TodoItem> store = new HashMap();

  // TODO: implement the fields to hold the instance state (id, text, completed)

  // TODO: implement the interface methods

  // force use of createItem method rather than direct instantiation
  private TodoItem(int id, String text) {
    // TODO: assign the id and text values
    this.completed = false; // initialize with incomplete status
  }

  public static TodoItem createItem(String text) {
    // TODO: instantiate the TodoItem instance using the private constructor
    TodoItem todoItem = null;
    int id = ++nextId; // auto-increment the Id to ensure unique
    
    // add it to the store
    store.put(id, todoItem);
  }

  public static TodoItem findItem(int id) {
    // TODO: implement the method for finding an item in the store
    // use the signature to guide your logic
    return null;
  }

  public static List<TodoItem> findAllItems() {
    // TODO: implement the method for getting all the items in the store
    // you will need to convert the HashMap to a List of TodoItem objects so that it can be sent as a JSON array
    return null;
  }

  public static boolean deleteItem(int id) {
    // TODO: implement the method for deleting an item in the store
    // use the signature to guide your logic
    return false;
  }
}