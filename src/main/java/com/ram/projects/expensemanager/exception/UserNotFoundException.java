package com.ram.projects.expensemanager.exception;

public class UserNotFoundException extends ExpMgrException{
    public UserNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public UserNotFoundException(String message) {
        super(message);
    }
    public UserNotFoundException() {
        super("User not found in DB!");
    }
}
