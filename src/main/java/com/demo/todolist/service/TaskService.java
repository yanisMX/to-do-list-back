// Interface dans com.demo.todolist.service
package com.demo.todolist.service;

import com.demo.todolist.model.Task;
import com.demo.todolist.utils.Priority;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    Task getTaskById(Long id);
    Task createTask(Task task, Long id);
    Task updateTask(Long id, Task updateTask);
    void deleteTask(Long id);
    List<Task> getTasksByPriority(String priority);
}
