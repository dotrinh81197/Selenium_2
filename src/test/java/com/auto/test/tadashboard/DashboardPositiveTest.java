package com.auto.test.tadashboard;

import com.auto.model.AlertMessage;
import com.auto.model.Page;
import com.auto.model.User;
import com.auto.page.tadashboard.DashboardPage;
import com.auto.page.tadashboard.LoginPage;
import com.auto.test.TestBase;
import com.auto.utils.Utilities;
import com.auto.utils.WebDriverUltis;
import com.logigear.statics.Selaium;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.auto.utils.Constants.LOGIN_PAGE_URL;

import static com.logigear.statics.Selaium.open;

public class DashboardPositiveTest extends TestBase {
    SoftAssert softAssert = new SoftAssert();

    //negative
    @Test(description = "Verify that user is unable open more than 1 New Page dialog")
    public void DA_MP_TC011_User_is_unable_open_more_than_1_New_Page_dialog() {
        open(LOGIN_PAGE_URL);

        LoginPage loginPage = new LoginPage();
        User user = new User();
        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage();
        softAssert.assertTrue(dashboardPage.doesContentDisplay());

        dashboardPage.clickAddPageBtn();

        dashboardPage.doesNewPageDialogTitleDisplay();

        softAssert.assertTrue(dashboardPage.isGlobalSettingLnkUnClickable());

        dashboardPage.logout();
    }

    @Test(description = "Verify that user is able to add additional pages besides Overview page successfully")
    public void DA_MP_TC012_User_is_able_to_add_additional_pages_besides_Overview_page_successfully() throws InterruptedException {

        open(LOGIN_PAGE_URL);

        LoginPage loginPage = new LoginPage();

        User user = new User();
        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage();
        softAssert.assertTrue(dashboardPage.doesContentDisplay());

        Page newPage = new Page("simplePage");
        dashboardPage.createNewPage(newPage);

        WebDriverUltis.waitForPageLoad();
        softAssert.assertTrue(dashboardPage.doesNewPageDisplayBeside(newPage.getPageName(), "Overview"));

        dashboardPage.removePage(newPage.getPageName());
        Selaium.pause(10000);
        dashboardPage.logout();
    }

    @Test(description = "Verify that the newly added main parent page is positioned at the location specified as set with Displayed After field of New Page form on the main page bar/Parent Page dropped down menu")
    public void DA_MP_TC013_The_newly_added_main_parent_page_is_positioned_at_the_location_specified_as_set_with_Displayed_After_field_of_New_Page_form_on_the_main_page_bar_Parent_Page_dropped_down_menu() throws InterruptedException {

        open(LOGIN_PAGE_URL);
        WebDriverUltis.waitForPageLoad();

        LoginPage loginPage = new LoginPage();

        User user = new User();
        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage();
        softAssert.assertTrue(dashboardPage.doesContentDisplay());

        // Add Test Page
        Page newPage = new Page("simplePage");
        dashboardPage.createNewPage(newPage);
        WebDriverUltis.waitForPageLoad();
        //Add another Test page
        Page newlyPage = new Page("simplePageAfterTestPagePage");
        dashboardPage.createNewPage(newlyPage);
        WebDriverUltis.waitForPageLoad();

        softAssert.assertTrue(dashboardPage.doesNewPageDisplayBeside(newlyPage.getPageName(), newPage.getPageName()));

        dashboardPage.removePage(newPage.getPageName());

        WebDriverUltis.waitForPageLoad();
        dashboardPage.removePage(newlyPage.getPageName());
        Selaium.pause(10000);
        dashboardPage.logout();
    }

    @Test(description = "Verify that user can remove any main parent page except \"Overview\" page successfully and the order of pages stays persistent as long as there is not children page under it")
    public void DA_MP_TC017_User_can_remove_any_Main_parent_page_except_Overview_page_and_the_order_of_pages_stay_persistent_is_not_children_page() {
        open(LOGIN_PAGE_URL);

        LoginPage loginPage = new LoginPage();

        User user = new User();
        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage();
        softAssert.assertTrue(dashboardPage.doesContentDisplay());

        Page parentPage = new Page("parentPage");
        dashboardPage.createNewPage(parentPage);

        WebDriverUltis.waitForPageLoad();

        Page childPage = new Page("childPage1");
        dashboardPage.createNewPage(childPage);

        dashboardPage.clickOnPage(parentPage.getPageName());
        dashboardPage.clickDeletePageBtn();

        AlertMessage alert = new AlertMessage("removePage");
        String actualAlertMessage = Utilities.getAlertMessage();
        String expectedMessage = alert.getAlertText();
        softAssert.assertEquals(actualAlertMessage, expectedMessage, "Verify alert message " + expectedMessage + "display");
        Selaium.pause(10000);
        WebDriverUltis.acceptAlert();

        Selaium.pause(10000);

        alert = new AlertMessage("cannotRemoveParentPageSinceHasChildren");
        actualAlertMessage = Utilities.getAlertMessage();
        System.out.println(actualAlertMessage);
        expectedMessage = String.format(alert.getAlertText(), parentPage.getPageName());
        softAssert.assertEquals(actualAlertMessage, expectedMessage, "Verify alert message " + expectedMessage + " display");
        WebDriverUltis.acceptAlert();

        dashboardPage.removeChildrenPage(parentPage.getPageName(), childPage.getPageName());
        alert = new AlertMessage("removePage");
        actualAlertMessage = Utilities.getAlertMessage();
        expectedMessage = alert.getAlertText();
        softAssert.assertEquals(actualAlertMessage, expectedMessage, "Verify alert message " + expectedMessage + "display");
        WebDriverUltis.acceptAlert();

        Selaium.pause(10000);
        dashboardPage.doesPageNotDisplay(childPage);

        dashboardPage.removePage(parentPage.getPageName());
        Selaium.pause(10000);
        dashboardPage.doesPageNotDisplay(parentPage);

        dashboardPage.clickOnPage("Overview");
        softAssert.assertTrue(dashboardPage.doesDeleteButtonIsNotDisplay());
        dashboardPage.logout();

    }

}


