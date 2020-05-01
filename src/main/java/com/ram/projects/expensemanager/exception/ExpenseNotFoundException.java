package com.ram.projects.expensemanager.exception;

public class ExpenseNotFoundException extends ExpMgrException{
    public ExpenseNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ExpenseNotFoundException(String message) {
        super(message);
    }
    public ExpenseNotFoundException() {
        super("No expenses found in DB!");
    }
}
