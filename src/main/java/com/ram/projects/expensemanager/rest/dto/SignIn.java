package com.ram.projects.expensemanager.rest.dto;

public final class SignIn {
  private final String userName;

  private final String password;

  private final String encryptionKey;

  public SignIn(String userName, String password, String encryptionKey) {
    this.userName = userName;
    this.password = password;
    this.encryptionKey = encryptionKey;
  }

  public String getUserName() {
    return userName;
  }

  public String getPassword() {
    return password;
  }

  public String getEncryptionKey() {
    return encryptionKey;
  }

  @Override
  public String toString() {
    return "SignIn{"
        + "userName='"
        + userName
        + '\''
        + ", password='"
        + password
        + '\''
        + ", encryptionKey='"
        + encryptionKey
        + '\''
        + '}';
  }
}
