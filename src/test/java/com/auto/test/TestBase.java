package com.auto.test;

import com.auto.page.tadashboard.DashboardPage;
import com.auto.utils.ExecutionContext;
import com.logigear.statics.Selaium;
import com.logigear.utils.ConfigLoader;
import com.logigear.utils.Configuration;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import org.testng.internal.annotations.IBeforeMethod;

import static com.auto.utils.Constants.ConfigFiles;
import static com.auto.utils.Constants.LOADING_TIME;

public class TestBase {

    private static final Logger log = LoggerFactory.getLogger(TestBase.class);
    Configuration config;

    @BeforeClass
    @Parameters("platform")
    public void beforeAll(@Optional String platform) {
        platform = java.util.Optional.ofNullable(platform).orElse("chrome");
        log.info("Running test on {}", platform);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-extensions");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        config = Configuration.defaultConfig(platform);
        config.setStartMaximized(true);
        config.setCapabilities(options);
        Selaium.setConfig(config);
    }

    @BeforeMethod
    public void beforeMethod(){

    }

    @AfterMethod
    public void afterMethod(){
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.logout();
    }

    @AfterClass
    public void afterAll() {
        Selaium.closeWebDriver();
    }

}
