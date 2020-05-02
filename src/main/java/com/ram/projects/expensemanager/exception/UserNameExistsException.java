package com.ram.projects.expensemanager.exception;

public class UserNameExistsException extends ExpMgrException{
    public UserNameExistsException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public UserNameExistsException(String message) {
        super(message);
    }
    public UserNameExistsException() {
        super("UserName not available for use, create another one");
    }
}
