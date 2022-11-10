package com.auto.test.tadashboard.panel;

import com.auto.model.AlertMessage;
import com.auto.model.Page;
import com.auto.model.Panel;
import com.auto.model.User;
import com.auto.page.tadashboard.DashboardPage;
import com.auto.page.tadashboard.LoginPage;
import com.auto.page.tadashboard.PanelPage;
import com.auto.test.TestBase;
import com.auto.utils.JsonUtils;
import com.auto.utils.WebDriverUltis;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Hashtable;

public class PanelPositiveTest extends TestBase {

    SoftAssert softAssert = new SoftAssert();
    User user = new User();
    LoginPage loginPage = new LoginPage();


    @Test(dataProvider = "getDataItemPanelAvailable", dataProviderClass = JsonUtils.class, description = "Verify that when Choose panels form is expanded all pre-set panels are populated and sorted correctly")
    public void DA_PANEL_TC027_Choose_Panel_form_expanded_all_pre_set_and_sorted_correctly(Hashtable<String, String> data) {
        System.out.println(data.get("ItemNumber16"));
        loginPage.login(user);
        DashboardPage dashboardPage = new DashboardPage();
        Page newPage = new Page();
        dashboardPage.createNewPage(newPage);
        Panel validDisplayNamePanel = new Panel();
        dashboardPage.createNewPanel(validDisplayNamePanel);
        dashboardPage.clickPanelConfigurationOKBtn();
        dashboardPage.clickChoosePanelBtn();

        softAssert.assertTrue(dashboardPage.doesItemDisplayCorrectly(data));


    }

    @Test(description = "Verify that when Add New Panel form is on focused all other control/form is disabled or locked")
    public void DA_PANEL_TC028_When_Add_New_Panel_form_is_on_focused_all_other_control_form_is_disabled_or_locked() {
        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.clickPanelLnk();

        PanelPage panelPage = new PanelPage();
        panelPage.clickAddNewLink();

        softAssert.assertFalse(panelPage.clickChoosePanelButton());
        softAssert.assertFalse(panelPage.clickChoosePanelButton());
        softAssert.assertFalse(panelPage.clickOnPage("Overview"));
        softAssert.assertFalse(panelPage.clickOnPage("Execution Dashboard"));

    }
    @Test(description = "Verify that when Add New Panel form is on focused all other control/form is disabled or locked")
    public void DA_PANEL_TC029_When_Add_New_Panel_form_is_on_focused_all_other_control_form_is_disabled_or_locked() {
        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.clickPanelLnk();

        PanelPage panelPage = new PanelPage();
        panelPage.clickAddNewLink();

        softAssert.assertFalse(panelPage.clickChoosePanelButton());
        softAssert.assertFalse(panelPage.clickChoosePanelButton());
        softAssert.assertFalse(panelPage.clickOnPage("Overview"));
        softAssert.assertFalse(panelPage.clickOnPage("Execution Dashboard"));

    }

    @Test(description = "Verify that no special character except '@' character is allowed to be inputted into Display Name field")
    public void DA_PANEL_TC030_No_special_character_except_character_is_allowed_to_be_inputted_into_Display_Name_field() {
        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.clickPanelLnk();

        PanelPage panelPage = new PanelPage();
        panelPage.clickAddNewLink();
        Panel invalidPanel = new Panel("panelInvalidDisplayName");
        panelPage.createNewPanel(invalidPanel);

        AlertMessage alertMessage = new AlertMessage("panelDisplayNameIsRequired");
        panelPage.doesAlertTextDisplay(alertMessage.getText());

        WebDriverUltis.acceptAlert();
        panelPage.clickAddNewLink();
        Panel panel = new Panel("panelValidDisplayName");
        panelPage.createNewPanel(panel);
        softAssert.assertTrue(panelPage.doesPanelDisplays(panel));

        panelPage.deleteAllPanels();

    }
}
