package com.auto.model;

import com.auto.data.enums.Data;
import com.auto.utils.JsonUtils;

import java.util.Hashtable;

public class UserModel {
    private String username;
    private String password;

    private static Hashtable<String, String> data;

    public UserModel(String typeOfUser) {
        data = JsonUtils.getData(typeOfUser, Data.USER);
        this.username = data.get("userName");
        this.password = data.get("password");
    }

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
