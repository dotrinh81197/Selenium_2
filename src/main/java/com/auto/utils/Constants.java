package com.auto.utils;

import java.time.Duration;
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
    public static final String LOGIN_PAGE_URL = "http://localhost:" + PropertiesFile.getPropertyValue("port") + "/TADashboard/login.jsp";
    public static final String VALID_USERNAME = "administrator";
    public static final String VALID_PASSWORD = "";
    public static final String INVALID_USERNAME_PASSWORD = "Username or password is invalid";
    public static final String TEST_REPOSITORY = "TestRepository";


    public static final int LOADING_TIME = 10000;
    public static final int LONG_TIME = 60;
    public static final int WAIT_TIME = 30;
    public static final int WAIT_TIME_3_SEC = 3;
    public static final Duration WAIT_TIME_DURATION = Duration.ofSeconds(60);


    static {
        ConfigFiles.put(ANDROID, "src/test/resources/configuration/android.json");
        ConfigFiles.put(IOS, "src/test/resources/configuration/ios.json");
        ConfigFiles.put(ACCOUNT, "src/test/resources/data/accounts.json");
        ConfigFiles.put(JIRA, "src/test/resources/configuration/jira.json");
        ConfigFiles.put(TEST_RAIL, "src/test/resources/configuration/testrail.json");
    }
}
