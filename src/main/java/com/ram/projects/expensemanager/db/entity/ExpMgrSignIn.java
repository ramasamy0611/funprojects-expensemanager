package com.ram.projects.expensemanager.db.entity;

import javax.persistence.*;

@Entity
@Table(name = "exp_mgr_signin")
public class ExpMgrSignIn {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_name")
  private String userName;

  @Column(name = "password")
  private String password;

  @Column(name = "encryption_key")
  private String encryptionKey;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public ExpMgrSignIn(String userName, String password, String encryptionKey) {
    this.userName = userName;
    this.password = password;
    this.encryptionKey = encryptionKey;
  }

  public ExpMgrSignIn() {}

  @Override
  public String toString() {
    return "SignIn{"
        + "id="
        + id
        + ", userName='"
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
