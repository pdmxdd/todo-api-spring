package org.launchcode.todo.data;

import org.launchcode.todo.Models.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<TodoItem, Integer> {
    
}
