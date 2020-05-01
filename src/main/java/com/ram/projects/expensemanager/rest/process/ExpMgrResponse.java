package com.ram.projects.expensemanager.rest.process;

public class ExpMgrResponse<T> {
    String message;
    T payload;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "ExpMgrResponse{" +
                "message='" + message + '\'' +
                ", payload=" + payload +
                '}';
    }
}
