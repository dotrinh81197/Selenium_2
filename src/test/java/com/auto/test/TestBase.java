package com.auto.test;

import com.auto.utils.ExecutionContext;
import com.logigear.statics.Selaium;
import com.logigear.utils.ConfigLoader;
import com.logigear.utils.Configuration;
import org.openqa.selenium.Capabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import static com.auto.utils.Constants.ConfigFiles;

public class TestBase {

    private static final Logger log = LoggerFactory.getLogger(TestBase.class);
    Configuration config;

    @BeforeClass
    @Parameters("platform")
    public void beforeAll(String platform) {
        config = ConfigLoader.fromJsonFile(ConfigFiles.get(platform));
        Selaium.setConfig(config);
        Selaium.open();
        Capabilities capabilities = Selaium.remoteWebDriver().getCapabilities();
        ExecutionContext.setEnvironment(capabilities.getCapability("platformName").toString(),
                capabilities.getCapability("deviceName").toString() + " - Version " + capabilities.getCapability("platformVersion").toString());
    }

    @AfterClass
    public void afterAll() {
        Selaium.closeWebDriver();
    }
}
