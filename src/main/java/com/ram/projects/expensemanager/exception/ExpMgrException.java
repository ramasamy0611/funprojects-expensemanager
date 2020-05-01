package com.ram.projects.expensemanager.exception;

public class ExpMgrException extends RuntimeException {
    private String expMgrExceptionMessage;
    public ExpMgrException(String message, Throwable throwable) {
        super(message, throwable);
        this.expMgrExceptionMessage = message;
    }

    public ExpMgrException(String message) {
        super(message);
        this.expMgrExceptionMessage = message;
    }

    public String getExpMgrExceptionMessage() {
        return expMgrExceptionMessage;
    }
}
