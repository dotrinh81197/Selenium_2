package com.auto.report;

import com.auto.integration.jira.JiraReporter;
import com.auto.utils.ExecutionContext;
import io.qameta.allure.listener.StepLifecycleListener;
import io.qameta.allure.model.StepResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StepListener implements StepLifecycleListener {

    private static final Logger log = LoggerFactory.getLogger(StepListener.class);

    @Override
    public void beforeStepStart(StepResult result) {
        log.info("[Step]: " + result.getName() + " is started");
        // Keep all steps of test case
        if (JiraReporter.instance().config().isLogBug()) {
            ExecutionContext.setSteps(result.getName());
        }
    }
}
