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
public class DashboardPositiveTest extends TestBase {
    User user = new User();
    LoginPage loginPage = new LoginPage();
    SoftAssert softAssert = new SoftAssert();

    @Test(description = "Verify that user is able to add additional pages besides Overview page successfully")
    public void DA_MP_TC012_User_is_able_to_add_additional_pages_besides_Overview_page_successfully() throws InterruptedException {

        open(LOGIN_PAGE_URL);

        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage();
        softAssert.assertTrue(dashboardPage.doesContentDisplay());

        Page newPage = new Page("simplePage");
        dashboardPage.createNewPage(newPage);

        WebDriverUltis.waitForPageLoad();
        softAssert.assertTrue(dashboardPage.doesNewPageDisplayBeside(newPage.getPageName(), "Overview"));

        dashboardPage.removePage(newPage.getPageName());
        Selaium.pause(10000);

    }

    @Test(description = "Verify that the newly added main parent page is positioned at the location specified as set with Displayed After field of New Page form on the main page bar/Parent Page dropped down menu")
    public void DA_MP_TC013_The_newly_added_main_parent_page_is_positioned_at_the_location_specified_as_set_with_Displayed_After_field_of_New_Page_form_on_the_main_page_bar_Parent_Page_dropped_down_menu() throws InterruptedException {

        open(LOGIN_PAGE_URL);
        WebDriverUltis.waitForPageLoad();

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

    }

    @Test(description = "Verify that user can remove any main parent page except \"Overview\" page successfully and the order of pages stays persistent as long as there is not children page under it")
    public void DA_MP_TC017_User_can_remove_any_Main_parent_page_except_Overview_page_and_the_order_of_pages_stay_persistent_is_not_children_page() {
        open(LOGIN_PAGE_URL);

        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage();
        softAssert.assertTrue(dashboardPage.doesContentDisplay());

        Page parentPage = new Page("parentPage");
        dashboardPage.createNewPage(parentPage);

        Selaium.pause(LOADING_TIME);

        Page childPage = new Page("childPage1");
        dashboardPage.createNewPage(childPage);
        Selaium.pause(LOADING_TIME);
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

        dashboardPage.removeChildrenPage(childPage, parentPage);
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

    }

    @Test(description = "Verify that user is able to add additional sibling pages to the parent page successfully")
    public void DA_MP_TC018_User_is_able_to_add_additional_sibling_pages_to_the_parent_page_successfully() {
        open(LOGIN_PAGE_URL);

        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage();
        Page parentPage = new Page("parentPage");
        dashboardPage.createNewPage(parentPage);

        Selaium.pause(LOADING_TIME);

        Page childPage = new Page("childPage1");
        dashboardPage.createNewPage(childPage);

        Selaium.pause(LOADING_TIME);

        Page childPage2 = new Page("childPage2");
        dashboardPage.createNewPage(childPage2);
        Selaium.pause(LOADING_TIME);

        softAssert.assertTrue(dashboardPage.doesNewPageDisplay(childPage2, childPage));
        dashboardPage.removeChildrenPage(childPage2, childPage);
        Selaium.pause(LOADING_TIME);
        dashboardPage.removeChildrenPage(childPage, parentPage);
        Selaium.pause(LOADING_TIME);
        dashboardPage.removePage(parentPage.getPageName());

    }

    @Test(description = "Verify that user is able to add additional sibbling page levels to the parent page successfully")
    public void DA_MP_TC019_User_is_able_to_add_additional_sibbling_page_levels_to_the_parent_page_successfully() {
        open(LOGIN_PAGE_URL);

        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage();
        Page page1 = new Page("page1");
        Page overViewPage = new Page("overviewPage");
        dashboardPage.createNewPage(page1);
        Selaium.pause(LOADING_TIME);
        softAssert.assertTrue(dashboardPage.doesNewPageDisplay(page1, overViewPage));
        dashboardPage.removeChildrenPage(page1, overViewPage);
        WebDriverUltis.waitForPageLoad();

    }

    @Test(description = "Verify that user is able to delete sibling page as long as that page has not children page under it")
    public void DA_MP_TC020_User_is_able_to_delete_sibling_page_as_long_as_that_page_has_not_children_page_under_it() {
        open(LOGIN_PAGE_URL);

        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage();
        Page overViewPage = new Page("overviewPage");
        Page page1 = new Page("page1");
        Page page2 = new Page("page2");
        dashboardPage.createNewPage(page1);
        Selaium.pause(LOADING_TIME);
        dashboardPage.createNewPage(page2);
        Selaium.pause(LOADING_TIME);
        dashboardPage.clickOnChildrenPage(page1, overViewPage);
        dashboardPage.clickDeletePageBtn();
        AlertMessage alertMessage = new AlertMessage("removePage");
        String actualAlertMessage = Utilities.getAlertMessage();
        String expectedMessage = alertMessage.getAlertText();
        softAssert.assertEquals(actualAlertMessage, expectedMessage, "Verify alert message " + expectedMessage + "display");
        WebDriverUltis.acceptAlert();
        Selaium.pause(LOADING_TIME);

        alertMessage = new AlertMessage("cannotRemoveParentPageSinceHasChildren");
        actualAlertMessage = Utilities.getAlertMessage();
        System.out.println(actualAlertMessage);
        expectedMessage = String.format(alertMessage.getAlertText(), page1.getPageName());
        softAssert.assertEquals(actualAlertMessage, expectedMessage, "Verify alert message " + expectedMessage + " display");
        WebDriverUltis.acceptAlert();

        Selaium.pause(LOADING_TIME);
        dashboardPage.removeChildrenPage(page2, page1);
        dashboardPage.doesPageNotDisplay(page2);

        dashboardPage.removeChildrenPage(page1, overViewPage);

    }

    @Test(description = "Verify that user is able to edit the name of the page (Parent/Sibbling) successfully")
    public void DA_MP_TC021_User_is_able_to_edit_the_name_of_the_page_Parent_Sibbling_successfully() {
        open(LOGIN_PAGE_URL);

        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage();
        Page page1 = new Page("page1");
        Page page2 = new Page("page2");
        dashboardPage.createNewPage(page1);
        Selaium.pause(LOADING_TIME);
        dashboardPage.createNewPage(page2);
        Selaium.pause(LOADING_TIME);

        dashboardPage.editPageName(page1, "Page3");
        Selaium.pause(LOADING_TIME);
        dashboardPage.doesNewPageDisplay(page1);
        dashboardPage.editPageName(page2, "Page4");
        Selaium.pause(LOADING_TIME);
        dashboardPage.doesNewPageDisplay(page2);
        Selaium.pause(LOADING_TIME);

        dashboardPage.removePage(page2.getPageName());
        Selaium.pause(LOADING_TIME);
        dashboardPage.removePage(page1.getPageName());
    }

    @Test(description = "Verify that user is able to edit the parent page of the sibbling page successfully")
    public void DA_MP_TC023_User_is_able_to_edit_the_parent_page_of_the_sibbling_page_successfully() {
        open(LOGIN_PAGE_URL);

        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage();
        Page page1 = new Page("page1");
        Page page2 = new Page("page2");
        dashboardPage.createNewPage(page1);
        Selaium.pause(LOADING_TIME);
        dashboardPage.createNewPage(page2);
        Selaium.pause(LOADING_TIME);

        dashboardPage.editPageName(page1, "Page3");
        Selaium.pause(LOADING_TIME);
        dashboardPage.doesNewPageDisplay(page1);

        dashboardPage.removePage(page2.getPageName());
        Selaium.pause(10000);
        dashboardPage.removePage(page1.getPageName());
    }

    @Test(description = "Verify that \"Bread Crums\" navigation is correct")
    public void DA_MP_TC024_Bread_Crums_navigation_correctly() {
        open(LOGIN_PAGE_URL);

        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage();
        Page overviewPage = new Page("overviewPage");
        Page page1 = new Page("page1");
        Page page2 = new Page("page2");
        dashboardPage.createNewPage(page1);
        Selaium.pause(LOADING_TIME);
        dashboardPage.createNewPage(page2);
        Selaium.pause(LOADING_TIME);

        dashboardPage.clickOnPage(page1.getPageName());
        String currentTitlePage = WebDriverUltis.getCurrentTitlePage();
        softAssert.assertTrue(currentTitlePage.contains(page1.getPageName()));

        Selaium.pause(LOADING_TIME);
        dashboardPage.clickOnPage(page2.getPageName());
        currentTitlePage = WebDriverUltis.getCurrentTitlePage();
        softAssert.assertTrue(currentTitlePage.contains(page2.getPageName()));

        dashboardPage.removePage(page2.getPageName());
        Selaium.pause(10000);
        dashboardPage.removePage(page1.getPageName());
    }

    @Test(description = "Verify that page listing is correct when user edit \"Display After\"  field of a specific page")
    public void DA_MP_TC025_Page_listing_is_correct_when_user_edit_Display_After_field_of_a_specific_page() {
        open(LOGIN_PAGE_URL);

        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage();
        Page overviewPage = new Page("overviewPage");
        Page page1 = new Page("simplePage");
        Page page2 = new Page("parentPage");
        dashboardPage.createNewPage(page1);
        Selaium.pause(LOADING_TIME);
        dashboardPage.createNewPage(page2);
        Selaium.pause(LOADING_TIME);

        dashboardPage.editDisplayAfter(page2, overviewPage.getPageName());
        WebDriverUltis.waitForPageLoad();
        dashboardPage.doesNewPageDisplayBeside(page2.getPageName(), overviewPage.getPageName());

        dashboardPage.removePage(page2.getPageName());
        Selaium.pause(10000);
        dashboardPage.removePage(page1.getPageName());
    }

}

