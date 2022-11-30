package com.auto.test.tadashboard;

import com.auto.model.AlertMessage;
import com.auto.model.Page;
import com.auto.model.User;
import com.auto.page.tadashboard.DashboardPage;
import com.auto.page.tadashboard.LoginPage;
import com.auto.test.TestBase;
import com.auto.testng.TestListener;
import com.auto.utils.Utilities;
import com.auto.utils.WebDriverUltis;
import com.logigear.statics.Selaium;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.auto.utils.Constants.LOADING_TIME;
import static com.auto.utils.Constants.LOGIN_PAGE_URL;
import static com.logigear.statics.Selaium.open;

@Listeners(TestListener.class)
public class DashboardNegativeTest extends TestBase {
    User user = new User();
    LoginPage loginPage = new LoginPage();
    SoftAssert softAssert = new SoftAssert();

    //negative
    @Test(description = "Verify that user is unable open more than 1 New Page dialog")
    public void DA_MP_TC011_User_is_unable_open_more_than_1_New_Page_dialog() {
        
        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage();
        softAssert.assertTrue(dashboardPage.doesContentDisplay());

        dashboardPage.clickAddPageBtn();
        dashboardPage.doesNewPageDialogTitleDisplay();

        softAssert.assertTrue(dashboardPage.isGlobalSettingLnkUnClickable());
        softAssert.assertAll();
        dashboardPage.clickCancelBtn();

    }

    @Test(description = "Verify that user is unable to duplicate the name of sibbling page under the same parent page")
    public void DA_MP_TC022_User_is_unable_to_duplicate_the_name_of_sibbling_page_under_the_same_parent_page() {
       
        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage();
        softAssert.assertTrue(dashboardPage.doesContentDisplay());

        Page parentPage = new Page();
        dashboardPage.createNewPage(parentPage);
        WebDriverUltis.waitForPageLoad();

        Page childPage = new Page();
        dashboardPage.createNewPage(childPage.getPageName(), parentPage.getPageName());
        WebDriverUltis.waitForPageLoad();
        dashboardPage.createNewPage(childPage.getPageName(), parentPage.getPageName());
        AlertMessage alertMessage = new AlertMessage("pageNameAlreadyExist", childPage.getPageName());
        String actualAlertMessage = Utilities.getAlertMessage();
        String expectedMessage = alertMessage.getAlertText();
        softAssert.assertEquals(actualAlertMessage, expectedMessage, "Verify alert message " + expectedMessage + "display");
        WebDriverUltis.acceptAlert();
        dashboardPage.clickCancelBtn();

        softAssert.assertAll();

    }
}
