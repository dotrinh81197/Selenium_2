package com.auto.report;


import com.logigear.statics.Selaium;
import io.qameta.allure.Allure;
import io.qameta.allure.listener.StepLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import org.openqa.selenium.OutputType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;

public class StepListener implements StepLifecycleListener {

    private static final Logger log = LoggerFactory.getLogger(StepListener.class);

    @Override
    public void beforeStepStart(StepResult result) {
        log.info("[Step]: " + result.getName() + " is started");
        // Keep all steps of test case

    }

    @Override
    public void beforeStepStop(StepResult result)  {
        if (result.getStatus().equals(Status.FAILED)) {
            try {
            ByteArrayInputStream input = new ByteArrayInputStream(Selaium.takeScreenShot(OutputType.BYTES));
            Allure.addAttachment("screenShot", input);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
