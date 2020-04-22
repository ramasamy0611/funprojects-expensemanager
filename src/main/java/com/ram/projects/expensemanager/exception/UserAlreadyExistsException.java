package com.ram.projects.expensemanager.exception;

import com.ram.projects.expensemanager.db.entity.ExpMgrUser;

public class UserAlreadyExistsException extends ExpMgrException {
    private final transient ExpMgrUser existingUser;

    public UserAlreadyExistsException(String message, ExpMgrUser existingUser, Throwable throwable) {
        super(message, throwable);
        this.existingUser = existingUser;
    }

    public UserAlreadyExistsException(String message, ExpMgrUser existingUser) {
        super(message);
        this.existingUser = existingUser;
    }

    public ExpMgrUser getExistingUser() {
        return existingUser;
    }
}
