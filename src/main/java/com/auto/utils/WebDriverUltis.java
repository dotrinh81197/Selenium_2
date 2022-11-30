package com.auto.utils;

import com.logigear.statics.Selaium;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.logigear.statics.Selaium.driver;
import static com.logigear.statics.Selaium.driverContainer;

public class WebDriverUltis {

    @Step("Accept alert pop up")
    public static void acceptAlert() {
        Selaium.confirm();
//        WebDriverUltis.waitForPageLoad();
    }


    @Step("Wait for alert display")
    public static void waitForAlertDisplays() {
        new WebDriverWait(driver().getWebDriver(), Duration.ofSeconds(Constants.WAIT_TIME_3_SEC)).until(ExpectedConditions.alertIsPresent());
    }

    public static void waitForPageLoad() {
        ExpectedCondition<Boolean> condition = driver -> {
            boolean readyState = ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            boolean activeJQuery = ((JavascriptExecutor) driver).executeScript("if (typeof jQuery != 'undefined') {return jQuery.active == 0; } else { return true; }").equals(true);
            return readyState && activeJQuery;
        };

        new WebDriverWait(driver().getWebDriver(), Duration.ofSeconds(Constants.WAIT_TIME)).until(condition);
    }

    public static void forceClick(WebElement element) {
        waitForPageLoad();
        ((JavascriptExecutor) driver().getWebDriver()).executeScript("arguments[0].click();", element);
    }

    public static String getCurrentTitlePage() {
        return driverContainer().title();}

}
