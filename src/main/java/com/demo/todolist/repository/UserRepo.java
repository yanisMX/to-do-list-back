package com.demo.todolist.repository;

import com.demo.todolist.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
}
