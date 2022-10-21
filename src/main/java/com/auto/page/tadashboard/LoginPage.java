package com.auto.page.tadashboard;

import com.auto.model.UserModel;
import com.logigear.element.Element;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.logigear.statics.Selaium.driver;


public class LoginPage {
    private final Element dropdown = new Element("//select[@id='repository']");
    private final Element userName_txt = new Element("//input[@id='username']");
    private final Element password_txt = new Element("//input[@id='password']");
    private final Element login_btn = new Element("//div[@class='btn-login']");

    @Step("Login by username and password")
    public void login(UserModel user) {
        enterUsernameTextBox(user.getUsername());
        enterPasswordTextBox(user.getPassword());
        clickLoginBtn();
    }

    @Step("Enter username value")
    public void enterUsernameTextBox(String value) {
        userName_txt.enter(value);
    }

    @Step("Enter password value")
    public void enterPasswordTextBox(String value) {
        password_txt.enter(value);
    }

    @Step("Click login button")
    public void clickLoginBtn() {
        login_btn.click();
    }

    @Step("Wait for alert display")
    public void waitForAlertDisplays() {
        Duration waitTime = Duration.ofSeconds(2);
        new WebDriverWait(driver().getWebDriver(), waitTime).until(ExpectedConditions.alertIsPresent());
    }

    @Step("Check Alert text display")
    public boolean doesAlertTextDisplays() {
        waitForAlertDisplays();
        String alertText = driver().getWebDriver().switchTo().alert().getText();
        return alertText.equalsIgnoreCase("Username or password is invalid");
    }

    @Step("Accept alert popup")
    public void acceptAlert() {
        driver().getWebDriver().switchTo().alert().accept();
    }

}
