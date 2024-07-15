package co.edu.uptc.student.model;

import java.io.Serializable;

public class User implements Serializable {

    private String UserCreate;
    private String PasswordCreate;

    public User() {
    }

    public User(String userCreate, String passwordCreate) {
        UserCreate = userCreate;
        PasswordCreate = passwordCreate;
    }

    public String getUserCreate() {
        return UserCreate;
    }

    public void setUserCreate(String userCreate) {
        UserCreate = userCreate;
    }

    public String getPasswordCreate() {
        return PasswordCreate;
    }

    public void setPasswordCreate(String passwordCreate) {
        PasswordCreate = passwordCreate;
    }

}
