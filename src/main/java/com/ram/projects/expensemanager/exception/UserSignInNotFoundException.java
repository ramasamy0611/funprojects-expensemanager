package com.ram.projects.expensemanager.exception;

public class UserSignInNotFoundException extends ExpMgrException {
    public UserSignInNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public UserSignInNotFoundException(String message) {
        super(message);
    }
    public UserSignInNotFoundException() {
        super("SignIn information doesn't exists please SignUp/Register");
    }
}
