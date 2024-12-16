package com.demo.todolist.controller;
import com.demo.todolist.model.Task;
import com.demo.todolist.serviceImpl.TaskServiceImpl;
import com.demo.todolist.utils.Priority;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskServiceImpl taskService;

    public TaskController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }


    @GetMapping("/test")
    public String index() {
        return "Bienvenue dans mon application";
    }

    @GetMapping("/")
    public ResponseEntity<List<Task>> getAll(){
       return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/tasks/id/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id){
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @GetMapping("/tasks/priority/{priority}")
    public ResponseEntity<List<Task>> getTasksByPriority(@PathVariable String priority) {
        try {
            Priority parsedPriority = Priority.valueOf(priority.toUpperCase());
            return ResponseEntity.ok(taskService.getTasksByPriority(String.valueOf(parsedPriority)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@RequestBody Task postTask){
        return ResponseEntity.ok(taskService.createTask(postTask));
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@RequestBody Task task,@PathVariable Long id){
        return ResponseEntity.ok(taskService.updateTask(id, task));
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.ok("Task deleted successfully");
    }
}