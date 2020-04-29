package com.ram.projects.expensemanager.rest;

public class RestRequest<T> {
    private String requestComment;
    private T requestPayLoad;

    public String getRequestComment() {
        return requestComment;
    }

    public void setRequestComment(String requestComment) {
        this.requestComment = requestComment;
    }

    public T getRequestPayLoad() {
        return requestPayLoad;
    }

    public void setRequestPayLoad(T requestPayLoad) {
        this.requestPayLoad = requestPayLoad;
    }
}
