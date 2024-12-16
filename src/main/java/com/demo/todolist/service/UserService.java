package com.demo.todolist.service;

import com.demo.todolist.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    User createUser(User user);
    User getUserById(Long id);
    User updateUser(Long id, String user);
    void deleteUser(Long id);


}
