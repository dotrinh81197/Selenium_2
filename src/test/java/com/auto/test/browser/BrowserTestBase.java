package com.auto.test.browser;

import com.logigear.statics.Selaium;
import com.logigear.utils.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.logging.Level;

public class BrowserTestBase {
    private static final Logger log = LoggerFactory.getLogger(BrowserTestBase.class);
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

    @AfterClass
    public void afterAll() {
        Selaium.closeWebDriver();
    }
}
