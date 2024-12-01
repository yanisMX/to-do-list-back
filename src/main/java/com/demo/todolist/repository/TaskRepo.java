package com.demo.todolist.repository;

import com.demo.todolist.model.Task;
import com.demo.todolist.utils.Priority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepo extends CrudRepository<Task, Long> {

    List<Task> findByPriority(Priority priority);
}
