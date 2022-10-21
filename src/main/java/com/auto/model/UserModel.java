package com.auto.model;

import java.util.Hashtable;

public class UserModel {
    private String username;
    private String password;

    private static Hashtable<String, String> data;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
