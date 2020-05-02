package com.ram.projects.expensemanager.rest.dto;

public class SignUp {
  private SignIn signIn;
  private User user;

  public SignUp(SignIn signIn, User user) {
    this.signIn = signIn;
    this.user = user;
  }

  public SignIn getSignIn() {
    return signIn;
  }

  public User getUser() {
    return user;
  }
}
