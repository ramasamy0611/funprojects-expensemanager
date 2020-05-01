package com.ram.projects.expensemanager.rest;

public class RestResponse<R> {
  private String message;
  private R responsePayLoad;

  public String getMessage() {
    return message;
  }

  public R getResponsePayLoad() {
    return responsePayLoad;
  }

  private RestResponse() {}

  public static RestResponse newResponse() {
    return new RestResponse();
  }

  public RestResponse message(String message) {
    this.message = message;
    return this;
  }

  public RestResponse responsePayLoad(R responsePayLoad) {
    this.responsePayLoad = responsePayLoad;
    return this;
  }
}
