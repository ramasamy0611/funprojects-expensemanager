package com.ram.projects.expensemanager.rest;

public class User {
    private long id;
    private String firstName;
    private String lastName;
    private String creationDate;
    private String modifiedDate;
    private String comment;
    private String emailId;
    private String type;

    public User() {
    }

    public User(long id, String firstName, String lastName, String creationDate, String modifiedDate, String emailId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.creationDate = creationDate;
        this.modifiedDate = modifiedDate;
        this.emailId = emailId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", modifiedDate='" + modifiedDate + '\'' +
                ", comment='" + comment + '\'' +
                ", emailId='" + emailId + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
