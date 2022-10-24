package com.auto.test.tadashboard;

import com.auto.model.User;
import com.auto.page.tadashboard.DashboardPage;
import com.auto.page.tadashboard.LoginPage;
import com.auto.test.TestBase;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.auto.utils.Constants.LOGIN_PAGE_URL;
import static com.logigear.statics.Selaium.open;

public class LoginPositiveTest extends TestBase {

    private LoginPage loginPage = new LoginPage();
    SoftAssert softAssert = new SoftAssert();


    @Test(description = "Verify that user can login specific repository successfully via Dashboard login page with correct credentials")
    public void DA_LOGIN_TC001_User_can_login_specific_repository_via_login_page() {
        open(LOGIN_PAGE_URL);

        User user = new User();
        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage();
        softAssert.assertTrue(dashboardPage.doesContentDisplay());

        dashboardPage.logout();
    }

}

