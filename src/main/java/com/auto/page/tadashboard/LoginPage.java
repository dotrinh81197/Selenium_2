package com.auto.page.tadashboard;

import com.auto.model.User;
import com.auto.model.UserModel;
import com.logigear.Driver;
import com.logigear.element.Element;
import com.logigear.statics.Selaium;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.logigear.statics.Selaium.driver;


public class LoginPage {
    private final Element dropdown = new Element("//select[@id='repository']");
    private final Element userName_txt = new Element("//input[@id='username']");
    private final Element password_txt = new Element("//input[@id='password']");
    private final Element login_btn = new Element("//div[@class='btn-login']");

    public void login(UserModel user) {
        userName_txt.enter(user.getUsername());
        password_txt.enter(user.getPassword());
        login_btn.click();
    }

    public void waitForAlertDisplays() {
        Duration waitTime = Duration.ofSeconds(2);
        new WebDriverWait(driver().getWebDriver(), waitTime).until(ExpectedConditions.alertIsPresent());
    }

    public boolean doesAlertTextDisplays() {
        waitForAlertDisplays();
        String alertText = driver().getWebDriver().switchTo().alert().getText();
        return alertText.equalsIgnoreCase("Username or password is invalid");
    }

    public void acceptAlert() {
        driver().getWebDriver().switchTo().alert().accept();
    }

}
