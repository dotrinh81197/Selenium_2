package com.auto.test.tadashboard;

import com.auto.model.AlertMessage;
import com.auto.model.User;
import com.auto.page.tadashboard.LoginPage;
import com.auto.test.TestBase;
import com.auto.utils.WebDriverUltis;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.auto.utils.Constants.INVALID_USERNAME_PASSWORD;
import static com.auto.utils.Constants.LOGIN_PAGE_URL;
import static com.logigear.statics.Selaium.open;

public class LoginNegativeTest extends TestBase {

    SoftAssert softAssert = new SoftAssert();


    @Test(description = "Verify that user fails to login specific repository successfully via Dashboard login page with incorrect credentials")
    public void DA_LOGIN_TC002_User_can_not_login_specific_repository_via_login_page_with_incorrect_credentials() {
        open(LOGIN_PAGE_URL);

        User user = new User("invalidUserNameAndInvalidPassword");
        LoginPage loginPage = new LoginPage();
        loginPage.login(user);

        softAssert.assertTrue(loginPage.doesAlertTextDisplay(INVALID_USERNAME_PASSWORD));

        WebDriverUltis.acceptAlert();
    }

    @Test(description = "Verify that user fails to log in specific repository successfully via Dashboard login page with correct username and incorrect password")
    public void DA_LOGIN_TC003_User_can_not_login_specific_repository_via_login_page_with_incorrect_username_password() {
        open(LOGIN_PAGE_URL);

        User user = new User("validUserNameAndInvalidPassword");
        LoginPage loginPage = new LoginPage();
        loginPage.login(user);

        softAssert.assertTrue(loginPage.doesAlertTextDisplay(INVALID_USERNAME_PASSWORD));

        WebDriverUltis.acceptAlert();
    }

    @Test(description = "Verify that the page works correctly for the case when no input entered to Password and Username field")
    public void DA_LOGIN_TC010_User_can_not_login_specific_repository_via_login_page_with_no_username_password() {
        open(LOGIN_PAGE_URL);

        User user = new User("blankUserNameAndPassword");
        LoginPage loginPage = new LoginPage();
        loginPage.login(user);

        AlertMessage alert = new AlertMessage("loginBlankUserNameAndPassword");
        String alertMessage = alert.getAlertText();
        softAssert.assertTrue(loginPage.doesAlertTextDisplay(alertMessage));

        WebDriverUltis.acceptAlert();
    }
}
