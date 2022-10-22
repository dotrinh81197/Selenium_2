package com.auto.test.tadashboard;

import com.auto.model.UserModel;
import com.auto.page.tadashboard.LoginPage;
import com.auto.test.TestBase;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.auto.utils.Constants.LOGIN_PAGE_URL;
import static com.logigear.statics.Selaium.open;

public class LoginNegativeTest extends TestBase {
    private UserModel user;
    private LoginPage loginPage = new LoginPage();
    SoftAssert softAssert = new SoftAssert();


    @Test(description = "Verify that user fails to login specific repository successfully via Dashboard login page with incorrect credentials")
    public void DA_LOGIN_TC002_User_can_not_login_specific_repository_via_login_page_with_incorrect_credentials() {
        open(LOGIN_PAGE_URL);

        user = new UserModel("invalidUserNameAndInvalidPassword");
        loginPage.login(user);

        softAssert.assertTrue(loginPage.doesAlertTextDisplays());

        loginPage.acceptAlert();
    }
}