package com.auto.test.tadashboard;

import com.auto.model.User;
import com.auto.model.UserModel;
import com.auto.page.tadashboard.LoginPage;
import com.auto.page.tadashboard.MainPage;
import com.auto.test.browser.BrowserTestBase;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.logigear.statics.Selaium.open;


public class DA_LOGIN_TC001 extends BrowserTestBase {
    private UserModel user;
    private LoginPage loginPage = new LoginPage();
    SoftAssert softAssert = new SoftAssert();


    @Test(description = "Verify that user can login specific repository successfully via Dashboard login page with correct credentials")
    public void DA_LOGIN_TC001_User_can_login_specific_repository_via_login_page() {
        Reporter.log("Step 1: Navigate to Dashboard login page");
        open("http://192.168.0.107/TADashboard/login.jsp");


        Reporter.log("Step 2: Enter valid username and password and Click on Login button");
        user = User.instance().getUser();
        loginPage.login(user);

        Reporter.log("Step 3: Verify that Dashboard Main page appears");
        MainPage mainPage = new MainPage();
        softAssert.assertTrue(mainPage.doesContentDisplays());

        Reporter.log("Post-Condition: Log out and Close Dashboard");
        mainPage.logout();
    }

}
