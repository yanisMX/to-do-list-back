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

    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user){
         User newUser = this.userService.createUser(user);
         return ResponseEntity.ok(newUser);
    }

}
