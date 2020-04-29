package com.ram.projects.expensemanager.exception;

public class PreRequisiteFailedException extends ExpMgrException{
    public PreRequisiteFailedException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public PreRequisiteFailedException(String message) {
        super(message);
    }
}
