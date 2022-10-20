package com.auto.test.tadashboard;

import com.auto.model.UserModel;
import com.auto.page.tadashboard.LoginPage;
import com.auto.page.tadashboard.MainPage;
import com.auto.test.browser.BrowserTestBase;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DA_LOGIN_TC002 extends BrowserTestBase {
    private UserModel user;
    private LoginPage loginPage = new LoginPage();
    SoftAssert softAssert = new SoftAssert();


    @Test(description = "Verify that user fails to login specific repository successfully via Dashboard login page with incorrect credentials\t\t\t\n")
    public void DA_LOGIN_TC002_User_can_not_login_specific_repository_via_login_page_with_incorrect_credentials() {
        Reporter.log("Step 1: Navigate to Dashboard login page");
//
        Reporter.log("Step 2: Enter invalid username and password");
        user = new UserModel("invalidUserNameAndInvalidPassword");
        loginPage.login(user);

        Reporter.log("Step 3: Verify that Dashboard Main page appears");

        softAssert.assertTrue(loginPage.doesAlertTextDisplays());

        Reporter.log("Post-Condition: Close message and Close Dashboard");
        loginPage.acceptAlert();
    }

}