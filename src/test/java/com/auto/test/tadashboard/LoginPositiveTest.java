package com.auto.test.tadashboard;

import com.auto.model.User;
import com.auto.page.tadashboard.DashboardPage;
import com.auto.page.tadashboard.LoginPage;
import com.auto.test.TestBase;
import com.auto.utils.Constants;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginPositiveTest extends TestBase {

    SoftAssert softAssert = new SoftAssert();
    User user = new User();
    LoginPage loginPage = new LoginPage();


    @Test(description = "Verify that user can login specific repository successfully via Dashboard login page with correct credentials")
    public void DA_LOGIN_TC001_User_can_login_specific_repository_via_login_page() {

        loginPage.login(user);
        DashboardPage dashboardPage = new DashboardPage();
        softAssert.assertTrue(dashboardPage.doesContentDisplay());

    }

    @Test(description = "Verify that user is able to log in different repositories successfully after logging out current repository")
    public void DA_LOGIN_TC004_User_can_login_different_repository_after_logged_current_repository() {

        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.logout();

        loginPage.selectRepository(Constants.TEST_REPOSITORY);
        loginPage.login(user);

        softAssert.assertTrue(dashboardPage.doesContentDisplay());

    }

    @Test(description = "Verify that there is no Login dialog when switching between 2 repositories with the same account")
    public void DA_LOGIN_TC005_No_logging_dialog_when_switching_repositories() {
        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.selectRepository(Constants.TEST_REPOSITORY);
        softAssert.assertTrue(loginPage.doesLoginPageNotDisplay());
        softAssert.assertTrue(dashboardPage.doesRepositoryNameDisplay(Constants.TEST_REPOSITORY));
    }

}

