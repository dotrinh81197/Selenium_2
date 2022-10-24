package com.auto.utils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExecutionContext {
//    static final Map<Long, UserModel> threadUser = new ConcurrentHashMap<>(4);
    static final ThreadLocal<List<String>> threadSteps = new InheritableThreadLocal<>();
    static final Map<String, String> environments = new HashMap<>();


//    public static void setUser(UserModel user) {
//        long threadId = currentThread().getId();
//        threadUser.remove(threadId);
//        threadUser.put(threadId, user);
//    }
//
//    public static UserModel getCurrentUser() {
//        long threadId = currentThread().getId();
//        return threadUser.get(threadId);
//    }

    public static void setEnvironment(String key, String value) {
        environments.put(key, value);
    }

    public static Map<String, String> getEnvironments() {
        return environments;
    }


    public static List<String> getSteps() {
        return threadSteps.get();
    }

    public static void cleanSteps() {
        List<String> list = threadSteps.get();
        if (list != null) {
            list.clear();
            threadSteps.set(list);
        }
    }

    public static void setSteps(String name) {
        List<String> list = threadSteps.get();
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(name);
        threadSteps.set(list);
    }
}
