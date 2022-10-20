package com.auto.integration.testrail;

import com.auto.integration.TestCase;
import com.auto.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.auto.utils.Constants.*;

public class TestRailReporter {
    private static final Logger log = LoggerFactory.getLogger(TestRailReporter.class);
    private final Map<String, Integer> status = new HashMap<>();
    private static TestRailReporter instance = null;
    private final TestRailConfiguration configuration;
    private final TestRailClient client;

    public static TestRailReporter instance() {
        if (instance == null) {
            instance = new TestRailReporter();
        }
        return instance;
    }

    private TestRailReporter() {
        status.put("failed", 5);
        status.put("passed", 1);
        this.configuration = JsonUtils.to(ConfigFiles.get(TEST_RAIL), TestRailConfiguration.class);
        this.client = new TestRailClient(configuration.getUrl(),
                configuration.getUsername(), configuration.getPassword());
    }

    public TestRailConfiguration config() {
        return this.configuration;
    }


    public void addResultForCase(String testCase, String status, String comment) {
        try {
            client.addResultForCase(configuration.getRunId(), caseId(testCase), this.status.get(status), comment);
        } catch (Exception ex) {
            log.error("Cannot add result for test case", ex);
        }

    }

    private int caseId(String name) {
        String temp = name;
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(name);
        if (m.find()) {
            temp = m.group();
        }
        return Integer.parseInt(temp);
    }

    public TestCase testCase(ITestResult result) {
        return result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(TestCase.class);
    }
}
