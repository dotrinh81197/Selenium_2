package com.auto.model;

import com.google.common.reflect.TypeToken;
import com.auto.utils.JsonUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.auto.utils.Constants.*;

public class User {
    private static User instance = null;
    private List<UserModel> users;

    private User() {
        Type userListType = new TypeToken<ArrayList<UserModel>>() {
        }.getType();
        this.users = JsonUtils.toList(ConfigFiles.get(ACCOUNT), userListType);
        System.out.println(this.users );
    }

    public List<UserModel> users() {
        return this.users;
    }

    public static User instance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }

    public UserModel getUser() {
//        Random r = new Random();
//        return this.users.get(r.nextInt(this.users.size()));
//        users.
        return this.users.get(0);
    }
}
