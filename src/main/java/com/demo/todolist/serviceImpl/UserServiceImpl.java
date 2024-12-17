package com.demo.todolist.serviceImpl;

import com.demo.todolist.exception.UserNotFoundException;
import com.demo.todolist.exception.UserNotValidException;
import com.demo.todolist.model.Task;
import com.demo.todolist.model.User;
import com.demo.todolist.repository.UserRepo;
import com.demo.todolist.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {


    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> getAllUsers() {
        return (List<User>) this.userRepo.findAll();
    }

    public User createUser(User user) {
        boolean isUserValid = testUserValidity(user);
        if (!isUserValid) {
            throw new UserNotValidException("L'utilisateur ne peut pas être créé");
        }
        this.userRepo.save(user);
        return user;
    }

    public User getUserById(Long id) {
        return this.userRepo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User updateUser(Long id, String newUsername) {
        User oldUser = this.userRepo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        String oldUsername = oldUser.getUsername();
        if (oldUsername != null) {
            oldUser.setUsername(newUsername);
        }
        log.info("L'utilisateur a été modifié");
        return oldUser;
    }

    public void deleteUser(Long id) {
        this.userRepo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        this.userRepo.deleteById(id);
        log.info("L'utilisateur a été supprimé");
    }

    public List<Task> getTasksByUser(Long id){
       User user = this.userRepo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
       return user.getTasks();
    }

    private boolean testUserValidity(User user) {
        return user.getUsername() != null && user.getEmail() != null && user.getPassword() != null;
    }

}
