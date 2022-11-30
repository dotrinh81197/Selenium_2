package com.auto.test;

import com.auto.page.tadashboard.DashboardPage;
import com.auto.page.tadashboard.PanelPage;
import com.auto.utils.PropertiesFile;
import com.auto.utils.Utilities;
import com.logigear.statics.Selaium;
import com.logigear.utils.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;

import static com.auto.utils.Constants.LOGIN_PAGE_URL;
import static com.logigear.statics.Selaium.open;

public class TestBase {

    private static final Logger log = LoggerFactory.getLogger(TestBase.class);
    Configuration config;

    @BeforeSuite
    public void beforeSuite() throws IOException {
        Utilities.deleteFiles(new File(System.getProperty("user.dir") + "/allure-results"));

    }

    @BeforeClass
    @Parameters("platform")
    public void beforeAll(@Optional String platform) {
        PropertiesFile data = new PropertiesFile();
        data.getProperties();
        platform = java.util.Optional.ofNullable(platform).orElse(data.getPropertyValue("browser"));
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
    public void beforeMethod() {
        open(LOGIN_PAGE_URL);
//        WebDriverUltis.waitForPageLoad();
    }

    @AfterMethod
    public void afterMethod() {
        DashboardPage dashboardPage = new DashboardPage();
        if (dashboardPage.doesContentDisplay()) {
            if (dashboardPage.doesCancelBtnEnable()) {
                dashboardPage.closeModalBtn();
            }
            Selaium.refresh();
            dashboardPage.removeAllPage();
            dashboardPage.gotoPanelPage();
            PanelPage panelPage = new PanelPage();
            panelPage.deleteAllPanels();
            dashboardPage.logout();
        } else Selaium.closeWebDriver();

    }

    @AfterClass
    public void afterAll() {
        Selaium.closeWebDriver();
    }

}
