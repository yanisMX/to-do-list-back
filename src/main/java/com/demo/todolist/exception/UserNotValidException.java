package com.demo.todolist.exception;

public class UserNotValidException extends RuntimeException {
    public UserNotValidException(String message) {
        super(message);
    }
}
