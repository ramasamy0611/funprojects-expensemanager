package com.ram.projects.expensemanager.rest;

public class RestResponse<R> {
    private String responseComment;
    private R responsePayLoad;

    public String getResponseComment() {
        return responseComment;
    }

    public void setResponseComment(String responseComment) {
        this.responseComment = responseComment;
    }

    public R getResponsePayLoad() {
        return responsePayLoad;
    }

    public void setResponsePayLoad(R responsePayLoad) {
        this.responsePayLoad = responsePayLoad;
    }
}
