package com.example.techthink.controller.model;

public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String userName;
    private String mail;
    private String password;
    private String description;
    //private String profilePictureURL;

    public RegisterRequest() {
    }

//    public RegisterRequest(String firstName, String lastName, String userName, String mail, String password, String description, String profilePictureURL) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.userName = userName;
//        this.mail = mail;
//        this.password = password;
//        this.description = description;
//        this.profilePictureURL = profilePictureURL;
//    }


    public RegisterRequest(String firstName, String lastName, String userName, String mail, String password, String description) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.mail = mail;
        this.password = password;
        this.description = description;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public String getProfilePictureURL() {
//        return profilePictureURL;
//    }
//
//    public void setProfilePictureURL(String profilePictureURL) {
//        this.profilePictureURL = profilePictureURL;
//    }
}
