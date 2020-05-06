package com.ram.projects.expensemanager.rest.dto;

public final class SignIn {
    private final int signInId;
    private final String userName;

    private final String password;

    private final String encryptionKey;

    public SignIn(int signInId, String userName, String password, String encryptionKey) {
        this.signInId = signInId;
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

    public int getSignInId() {
        return signInId;
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
