package com.ram.projects.expensemanager.db.entity;

public class ExpMgrSignUp {
  private ExpMgrSignIn expMgrSignIn;
  private ExpMgrUser expMgrUser;

  public ExpMgrSignUp(ExpMgrSignIn expMgrSignIn, ExpMgrUser expMgrUser) {
    this.expMgrSignIn = expMgrSignIn;
    this.expMgrUser = expMgrUser;
  }

  public ExpMgrSignIn getExpMgrSignIn() {
    return expMgrSignIn;
  }

  public ExpMgrUser getExpMgrUser() {
    return expMgrUser;
  }

  @Override
  public String toString() {
    return "ExpMgrSignUn{" + "expMgrSignIn=" + expMgrSignIn + ", expMgrUser=" + expMgrUser + '}';
  }
}
