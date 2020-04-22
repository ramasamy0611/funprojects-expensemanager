package com.ram.projects.expensemanager.exception;

public class ExpMgrException extends RuntimeException {
    public ExpMgrException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ExpMgrException(String message) {
        super(message);
    }

}
