package com.auto.page.tadashboard;

import com.auto.model.User;
import com.auto.utils.Element;
import com.auto.utils.WebDriverUltis;

import io.qameta.allure.Step;

import static com.logigear.statics.Selaium.driver;


public class LoginPage {
    private final Element repositoryDrl = new Element("//select[@id='repository']");
    private final Element userNameTxt = new Element("//input[@id='username']");
    private final Element passwordTxt = new Element("//input[@id='password']");
    private final Element loginBtn = new Element("//div[@class='btn-login']");
    private final Element loginForm = new Element("//div[@class='form']");

    @Step("Login by username and password")
    public void login(User user) {
        WebDriverUltis.waitForPageLoad();
        enterUsernameTxt(user.getUsername());
        enterPasswordTxt(user.getPassword());
        clickLoginBtn();
    }

    @Step("Enter username value")
    public void enterUsernameTxt(String value) {
        WebDriverUltis.waitForPageLoad();
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



    @Step("Check Alert text display")
    public boolean doesAlertTextDisplay(String text) {
        WebDriverUltis.waitForAlertDisplays();
        String alertText = driver().getWebDriver().switchTo().alert().getText();
        return alertText.equalsIgnoreCase(text);
    }

    @Step("Select repository")
    public void selectRepository(String repositoryName) {
        repositoryDrl.select(repositoryName);
    }

    @Step("Check Login page not display")
    public boolean doesLoginPageNotDisplay() {
        return !loginForm.isDisplayed();
    }
}
