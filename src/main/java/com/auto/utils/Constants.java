package com.auto.utils;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static final Map<String, String> ConfigFiles = new HashMap<>();
    public static final String ENV_ALLURE_FILE = "allure-results/environment.properties";
    public static final String ANDROID = "android";
    public static final String IOS = "ios";
    public static final String ACCOUNT = "account";
    public static final String JIRA = "jira";
    public static final String TEST_RAIL = "testrail";
    public static final String LOGIN_PAGE_URL = "http://localhost/TADashboard/login.jsp";


    public static final int LOADING_TIME = 5;
    public static final int LONG_TIME = 60;

    static {
        ConfigFiles.put(ANDROID, "src/test/resources/configuration/android.json");
        ConfigFiles.put(IOS, "src/test/resources/configuration/ios.json");
        ConfigFiles.put(ACCOUNT, "src/test/resources/data/accounts.json");
        ConfigFiles.put(JIRA, "src/test/resources/configuration/jira.json");
        ConfigFiles.put(TEST_RAIL, "src/test/resources/configuration/testrail.json");
    }
}
