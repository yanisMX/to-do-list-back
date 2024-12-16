package com.demo.todolist.serviceImpl;


import com.demo.todolist.exception.TaskNotFoundException;
import com.demo.todolist.exception.TaskNotValidException;
import com.demo.todolist.repository.TaskRepo;
import com.demo.todolist.model.Task;
import com.demo.todolist.service.TaskService;
import com.demo.todolist.utils.Priority;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class TaskServiceImpl implements TaskService {


    @Autowired
    private final TaskRepo taskRepo;

    public TaskServiceImpl(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public List<Task> getAllTasks() {
        return (List<Task>) taskRepo.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepo.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public List<Task> getTasksByPriority(String priority) {
        Priority parsedPriority = parsePriority(priority);
        return taskRepo.findByPriority(parsedPriority);
    }

    private Priority parsePriority(String priority) {
        try {
            return Priority.valueOf(priority.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid priority value: " + priority);
        }
    }

    public Task createTask(Task task) {
        if (task.getTitle().isEmpty() || task.getDescription().isEmpty() || !validatePriority(task.getPriority())) {
            throw new TaskNotValidException("Vous devez remplir tous les champs");
        }
        log.info("La tâche a été correctement sauvegardée");
        return taskRepo.save(task);
    }

    public Task updateTask(Long id, Task updateTask) {

        Task existingTask = taskRepo.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        if (updateTask.getTitle() != null) {
            existingTask.setTitle(updateTask.getTitle());
        }
        if (updateTask.getPriority() != null) {
            existingTask.setPriority(updateTask.getPriority()); // Correction pour le champ Priority
        }
        if (updateTask.getDescription() != null) {
            existingTask.setDescription(updateTask.getDescription()); // Correction pour Description
        }
        log.info("Tâche mise à jour avec succès : {}", existingTask);
        return taskRepo.save(existingTask);
    }


    public void deleteTask(Long id) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        log.info("La tâche a été correctement supprimée");
        taskRepo.delete(task);
    }


    private boolean validatePriority(Priority priority) {
        return (priority == Priority.HIGH || priority == Priority.MEDIUM || priority == Priority.LOW);
    }

}



