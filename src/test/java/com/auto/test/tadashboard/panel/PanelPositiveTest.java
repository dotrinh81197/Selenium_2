package com.auto.test.tadashboard.panel;

import com.auto.model.User;
import com.auto.page.tadashboard.LoginPage;
import com.auto.test.TestBase;
import com.auto.utils.DataProviderUtil;
import com.auto.utils.WebDriverUltis;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.auto.utils.Constants.INVALID_USERNAME_PASSWORD;

public class PanelPositiveTest extends TestBase {

    SoftAssert softAssert = new SoftAssert();

    @Test(description = "Verify that user fails to login specific repository successfully via Dashboard login page with incorrect credentials", dataProviderClass = DataProviderUtil.class, dataProvider = "getTypeUserData")
    public void DA_LOGIN_User_can_not_login_specific_repository_via_login_page_with_incorrect_credentials(String invalidUserType) {

        User user = new User(invalidUserType);
        LoginPage loginPage = new LoginPage();
        loginPage.login(user);

        softAssert.assertTrue(loginPage.doesAlertTextDisplay(INVALID_USERNAME_PASSWORD));
        WebDriverUltis.acceptAlert();
    }
}