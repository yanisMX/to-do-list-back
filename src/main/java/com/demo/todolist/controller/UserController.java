package com.demo.todolist.controller;


import com.demo.todolist.model.Task;
import com.demo.todolist.model.User;
import com.demo.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<User> getAllUsers(){
        return this.userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User user = this.userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user){
         User newUser = this.userService.createUser(user);
         return ResponseEntity.ok(newUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        this.userService.deleteUser(id);
        return ResponseEntity.ok("L'utilisateur a bien été supprimé");
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<List<Task>> getTasksByUser(@PathVariable Long id){
        List<Task> userTasks = this.userService.getTasksByUser(id);
        if (userTasks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(userTasks);
    }

}
