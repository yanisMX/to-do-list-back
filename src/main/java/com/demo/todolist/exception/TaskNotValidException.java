package com.demo.todolist.exception;

public class TaskNotValidException extends RuntimeException {
    public TaskNotValidException(String message) {
        super(message);
    }
}
