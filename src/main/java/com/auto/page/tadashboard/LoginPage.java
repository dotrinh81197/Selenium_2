package com.auto.page.tadashboard;

import com.auto.model.User;
import com.logigear.element.Element;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.logigear.statics.Selaium.driver;


public class LoginPage {
    private final Element repositoryDrl = new Element("//select[@id='repository']");
    private final Element userNameTxt = new Element("//input[@id='username']");
    private final Element passwordTxt = new Element("//input[@id='password']");
    private final Element loginBtn = new Element("//div[@class='btn-login']");

    @Step("Login by username and password")
    public void login(User user) {
        enterUsernameTxt(user.getUsername());
        enterPasswordTxt(user.getPassword());
        clickLoginBtn();
    }

    @Step("Enter username value")
    public void enterUsernameTxt(String value) {
        userNameTxt.enter(value);
    }

    @Step("Enter password value")
    public void enterPasswordTxt(String value) {
        passwordTxt.enter(value);
    }

    @Step("Click login button")
    public void clickLoginBtn() {
        loginBtn.click();
    }

    @Step("Wait for alert display")
    public void waitForAlertDisplays() {
        Duration waitTime = Duration.ofSeconds(2);
        new WebDriverWait(driver().getWebDriver(), waitTime).until(ExpectedConditions.alertIsPresent());
    }

    @Step("Check Alert text display")
    public boolean doesAlertTextDisplay(String text) {
        waitForAlertDisplays();
        String alertText = driver().getWebDriver().switchTo().alert().getText();
        return alertText.equalsIgnoreCase(text);
    }

}
