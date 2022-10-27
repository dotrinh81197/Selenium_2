package com.auto.model;

import com.auto.data.enums.Data;
import com.auto.utils.Constants;
import com.auto.utils.JsonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Hashtable;

@lombok.Data
public class User {
    private String username;
    private String password;

    private static Hashtable<String, String> data;

    public User() {
        this.username = Constants.VALID_USERNAME;
        this.password = Constants.VALID_PASSWORD;
    }

    public User(String typeOfUser) {
        data = JsonUtils.getData(typeOfUser, Data.INVALID_USER);
        this.username = data.get("userName");
        this.password = data.get("password");
    }

}
