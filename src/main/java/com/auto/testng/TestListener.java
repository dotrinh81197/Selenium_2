package com.auto.testng;

import com.auto.integration.TestCase;
import com.auto.integration.jira.JiraReporter;
import com.auto.integration.testrail.TestRailReporter;
import com.auto.utils.Constants;
import com.auto.utils.ExecutionContext;
import com.auto.utils.FileUtils;
import com.logigear.statics.Selaium;
import io.qameta.allure.Allure;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.OutputType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.util.Properties;


public class TestListener implements ITestListener {

    private static final Logger log = LoggerFactory.getLogger(TestListener.class);

    @Override
    public void onStart(ITestContext context) {
        log.info("Tests started on {}", context.getCurrentXmlTest().getParameter("platform"));
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Test case \"{} - {}\" is started", result.getMethod().getMethodName(),
                result.getMethod().getDescription());

        // Clean all cached steps of previous test
        if (JiraReporter.instance().config().isLogBug()) {
            ExecutionContext.cleanSteps();
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        // Add Environment information to Allure report
        Properties properties = new Properties();
        properties.putAll(ExecutionContext.getEnvironments());
        FileUtils.savePropertiesToFile(properties, Constants.ENV_ALLURE_FILE);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            if (Selaium.driverContainer().isAlive()) {
                ByteArrayInputStream input = new ByteArrayInputStream(Selaium.takeScreenShot(OutputType.BYTES));
                Allure.addAttachment("screenShot", input);
            }
            if (result.getThrowable() instanceof AssertionError) {
                // Submit bugs to Jira automatically
                if (JiraReporter.instance().config().isLogBug()) {
                    JiraReporter.instance().processBug(result.getMethod().getDescription(),
                            ExecutionContext.getSteps(), "");
                }

                // Submit test result into TestRail
                addResultTestRail(result);
            }
        } catch (Exception ex) {
            log.error("Error occurred", ex);
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Submit test result into TestRail
        addResultTestRail(result);
    }

    private void addResultTestRail(ITestResult result) {
        if (TestRailReporter.instance().config().isPublishResult()) {
            String description = result.getMethod().getDescription();
            TestCase testCase = TestRailReporter.instance().testCase(result);
            if (testCase != null && !StringUtils.isEmpty(testCase.id()) && !testCase.selfReporting()) {
                log.info("Publish test result of \"{}\" to Test Rail", description);
                String comment = result.getStatus() == ITestResult.SUCCESS ? "This test worked fine" : result.getThrowable().getMessage();
                String status = result.getStatus() == ITestResult.SUCCESS ? "passed" : "failed";
                TestRailReporter.instance().addResultForCase(testCase.id(), status, comment);
            }
        }
    }
}

