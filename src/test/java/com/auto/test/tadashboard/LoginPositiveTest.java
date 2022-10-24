package com.auto.test.tadashboard;

import com.auto.model.Repository;
import com.auto.model.User;
import com.auto.page.tadashboard.DashboardPage;
import com.auto.page.tadashboard.LoginPage;
import com.auto.test.TestBase;
import com.auto.utils.Constants;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.logigear.statics.Selaium.open;

public class LoginPositiveTest extends TestBase {

    SoftAssert softAssert = new SoftAssert();

    @Test(description = "Verify that user can login specific repository successfully via Dashboard login page with correct credentials")
    public void DA_LOGIN_TC001_User_can_login_specific_repository_via_login_page() {
        open(Constants.LOGIN_PAGE_URL);

        User user = new User();
        LoginPage loginPage = new LoginPage();
        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage();
        softAssert.assertTrue(dashboardPage.doesContentDisplay());

        dashboardPage.logout();
    }

    @Test(description = "Verify that user is able to log in different repositories successfully after logging out current repository")
    public void DA_LOGIN_TC004_User_can_login_different_repository_after_logged_current_repository() {

        open(Constants.LOGIN_PAGE_URL);

        User user = new User();
        LoginPage loginPage = new LoginPage();
        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.logout();

        Repository repository = new Repository(Constants.TEST_REPOSITORY);
        loginPage.selectRepository(repository);
        loginPage = new LoginPage();
        loginPage.login(user);

        softAssert.assertTrue(dashboardPage.doesContentDisplay());
        dashboardPage = new DashboardPage();
        dashboardPage.logout();

    }

    @Test(description = "Verify that there is no Login dialog when switching between 2 repositories with the same account")
    public void DA_LOGIN_TC005_No_logging_dialog_when_switching_repositories() {

        open(Constants.LOGIN_PAGE_URL);

        User user = new User();
        LoginPage loginPage = new LoginPage();
        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage();
        Repository repository = new Repository(Constants.TEST_REPOSITORY);
        dashboardPage.selectRepository(repository);
        softAssert.assertTrue(loginPage.doesLoginPageNotDisplay());
        dashboardPage = new DashboardPage();
        softAssert.assertTrue(dashboardPage.doesRepositoryNameDisplay(repository));

    }

}

