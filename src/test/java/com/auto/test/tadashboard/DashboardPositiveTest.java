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
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Listeners(TestListener.class)
public class DashboardPositiveTest extends TestBase {
    User user = new User();
    LoginPage loginPage = new LoginPage();
    SoftAssert softAssert = new SoftAssert();

    @Test(description = "Verify that user is able to add additional pages besides Overview page successfully")
    public void DA_MP_TC012_User_is_able_to_add_additional_pages_besides_Overview_page_successfully() throws InterruptedException {

        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage();
        softAssert.assertTrue(dashboardPage.doesContentDisplay());

        Page newPage = new Page();
        dashboardPage.createNewPage(newPage);

        WebDriverUltis.waitForPageLoad();
        softAssert.assertTrue(dashboardPage.doesNewPageDisplayBeside(newPage.getPageName(), "Overview"));

        dashboardPage.removePage(newPage.getPageName());

    }

    @Test(description = "Verify that the newly added main parent page is positioned at the location specified as set with Displayed After field of New Page form on the main page bar/Parent Page dropped down menu")
    public void DA_MP_TC013_The_newly_added_main_parent_page_is_positioned_at_the_location_specified_as_set_with_Displayed_After_field_of_New_Page_form_on_the_main_page_bar_Parent_Page_dropped_down_menu() {

        WebDriverUltis.waitForPageLoad();

        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage();
        softAssert.assertTrue(dashboardPage.doesContentDisplay());

        // Add Test Page
        Page newPage = new Page();
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

    }

    @Test(description = "Verify that user can remove any main parent page except 'Overview' page successfully and the order of pages stays persistent as long as there is not children page under it")
    public void DA_MP_TC017_User_can_remove_any_Main_parent_page_except_Overview_page_and_the_order_of_pages_stay_persistent_is_not_children_page() {

        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage();
        softAssert.assertTrue(dashboardPage.doesContentDisplay());

        Page parentPage = new Page("parentPage");
        dashboardPage.createNewPage(parentPage);

        Page childPage = new Page("childPage1");
        dashboardPage.createNewPage(childPage);
        WebDriverUltis.waitForPageLoad();

        dashboardPage.clickOnPage(parentPage.getPageName());
        dashboardPage.clickDeletePageBtn();

        AlertMessage alert = new AlertMessage("removePage");
        String actualAlertMessage = Utilities.getAlertMessage();
        String expectedMessage = alert.getAlertText();
        softAssert.assertEquals(actualAlertMessage, expectedMessage, "Verify alert message " + expectedMessage + "display");
        WebDriverUltis.acceptAlert();

        alert = new AlertMessage("cannotRemoveParentPageSinceHasChildren");
        actualAlertMessage = Utilities.getAlertMessage();
        expectedMessage = String.format(alert.getAlertText(), parentPage.getPageName());
        softAssert.assertEquals(actualAlertMessage, expectedMessage, "Verify alert message " + expectedMessage + " display");
        WebDriverUltis.acceptAlert();

        dashboardPage.clickOnPage(childPage.getPageName());
        dashboardPage.clickDeletePageBtn();
        alert = new AlertMessage("removePage");
        actualAlertMessage = Utilities.getAlertMessage();
        expectedMessage = alert.getAlertText();
        softAssert.assertEquals(actualAlertMessage, expectedMessage, "Verify alert message " + expectedMessage + "display");
        WebDriverUltis.acceptAlert();

        dashboardPage.doesPageNotDisplay(childPage);
        dashboardPage.removePage(parentPage.getPageName());
        WebDriverUltis.waitForPageLoad();
        dashboardPage.doesPageNotDisplay(parentPage);

        dashboardPage.clickOnPage("Overview");
        softAssert.assertTrue(dashboardPage.doesDeleteButtonIsNotDisplay());

    }

    @Test(description = "Verify that user is able to add additional sibling pages to the parent page successfully")
    public void DA_MP_TC018_User_is_able_to_add_additional_sibling_pages_to_the_parent_page_successfully() {


        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage();
        Page parentPage = new Page("parentPage");
        dashboardPage.createNewPage(parentPage);

        Page childPage = new Page("childPage1");
        dashboardPage.createNewPage(childPage);

        Page childPage2 = new Page("childPage2");
        dashboardPage.createNewPage(childPage2);

        softAssert.assertTrue(dashboardPage.doesNewPageDisplay(childPage2, childPage));
        dashboardPage.removePage(childPage2.getPageName());
        WebDriverUltis.waitForPageLoad();
        dashboardPage.removePage(childPage.getPageName());
        WebDriverUltis.waitForPageLoad();
        dashboardPage.removePage(parentPage.getPageName());

    }

    @Test(description = "Verify that user is able to add additional sibbling page levels to the parent page successfully")
    public void DA_MP_TC019_User_is_able_to_add_additional_sibbling_page_levels_to_the_parent_page_successfully() {

        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage();
        Page page1 = new Page("page1");
        Page overViewPage = new Page("overviewPage");
        dashboardPage.createNewPage(page1);
        WebDriverUltis.waitForPageLoad();

        softAssert.assertTrue(dashboardPage.doesNewPageDisplay(page1, overViewPage));
        dashboardPage.removeChildrenPage(page1, overViewPage);
    }

    @Test(description = "Verify that user is able to delete sibling page as long as that page has not children page under it")
    public void DA_MP_TC020_User_is_able_to_delete_sibling_page_as_long_as_that_page_has_not_children_page_under_it() {

        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage();
        Page overViewPage = new Page("overviewPage");
        Page page1 = new Page("page1");
        Page page2 = new Page("page2");
        dashboardPage.createNewPage(page1);
        WebDriverUltis.waitForPageLoad();
        dashboardPage.createNewPage(page2);
        WebDriverUltis.waitForPageLoad();
        dashboardPage.clickOnPage(page1.getPageName());
        dashboardPage.clickDeletePageBtn();
        AlertMessage alertMessage = new AlertMessage("removePage");
        String actualAlertMessage = Utilities.getAlertMessage();
        String expectedMessage = alertMessage.getAlertText();
        softAssert.assertEquals(actualAlertMessage, expectedMessage, "Verify alert message " + expectedMessage + "display");
        WebDriverUltis.acceptAlert();

        alertMessage = new AlertMessage("cannotRemoveParentPageSinceHasChildren");
        actualAlertMessage = Utilities.getAlertMessage();
        System.out.println(actualAlertMessage);
        expectedMessage = String.format(alertMessage.getAlertText(), page1.getPageName());
        softAssert.assertEquals(actualAlertMessage, expectedMessage, "Verify alert message " + expectedMessage + " display");
        WebDriverUltis.acceptAlert();

        dashboardPage.removeChildrenPage(page2, page1);
        dashboardPage.doesPageNotDisplay(page2);
        dashboardPage.removeChildrenPage(page1, overViewPage);

    }

    @Test(description = "Verify that user is able to edit the name of the page (Parent/Sibbling) successfully")
    public void DA_MP_TC021_User_is_able_to_edit_the_name_of_the_page_Parent_Sibbling_successfully() {


        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage();
        Page page1 = new Page("page1");
        Page page2 = new Page("page2");
        dashboardPage.createNewPage(page1);
        WebDriverUltis.waitForPageLoad();
        dashboardPage.createNewPage(page2);

        dashboardPage.editPageName(page1, "Page3");

        dashboardPage.doesNewPageDisplay(page1);
        dashboardPage.editPageName(page2, "Page4");

        dashboardPage.doesNewPageDisplay(page2);

        dashboardPage.removePage(page2.getPageName());
        dashboardPage.removePage(page1.getPageName());
    }

    @Test(description = "Verify that user is able to edit the parent page of the sibbling page successfully")
    public void DA_MP_TC023_User_is_able_to_edit_the_parent_page_of_the_sibbling_page_successfully() {


        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage();
        Page page1 = new Page("page1");
        Page page2 = new Page("page2");
        dashboardPage.createNewPage(page1);
        WebDriverUltis.waitForPageLoad();
        dashboardPage.createNewPage(page2);

        dashboardPage.editPageName(page1, "Page3");

        dashboardPage.doesNewPageDisplay(page1);

        dashboardPage.removePage(page2.getPageName());
        dashboardPage.removePage(page1.getPageName());
    }

    @Test(description = "Verify that 'Bread Crums' navigation is correct")
    public void DA_MP_TC024_Bread_Crums_navigation_correctly() {

        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage();
        Page page1 = new Page("page1");
        Page page2 = new Page("page2");
        dashboardPage.createNewPage(page1);
        WebDriverUltis.waitForPageLoad();
        dashboardPage.createNewPage(page2);

        dashboardPage.clickOnPage(page1.getPageName());
        String currentTitlePage = WebDriverUltis.getCurrentTitlePage();
        softAssert.assertTrue(currentTitlePage.contains(page1.getPageName()));

        dashboardPage.clickOnPage(page2.getPageName());
        currentTitlePage = WebDriverUltis.getCurrentTitlePage();
        softAssert.assertTrue(currentTitlePage.contains(page2.getPageName()));

        dashboardPage.removePage(page2.getPageName());
        WebDriverUltis.waitForPageLoad();
        dashboardPage.removePage(page1.getPageName());
    }

    @Test(description = "Verify that page listing is correct when user edit 'Display After'  field of a specific page")
    public void DA_MP_TC025_Page_listing_is_correct_when_user_edit_Display_After_field_of_a_specific_page() {

        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage();
        Page overviewPage = new Page("overviewPage");
        Page page1 = new Page();
        Page page2 = new Page("parentPage");
        dashboardPage.createNewPage(page1);
        WebDriverUltis.waitForPageLoad();
        dashboardPage.createNewPage(page2);

        dashboardPage.editDisplayAfter(page2, overviewPage.getPageName());
        WebDriverUltis.waitForPageLoad();
        dashboardPage.doesNewPageDisplayBeside(page2.getPageName(), overviewPage.getPageName());

        dashboardPage.removePage(page2.getPageName());
        WebDriverUltis.waitForPageLoad();
        dashboardPage.removePage(page1.getPageName());
    }

}


