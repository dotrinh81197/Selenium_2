package com.auto.test.tadashboard.panel;

import com.auto.model.AlertMessage;
import com.auto.model.Page;
import com.auto.model.Panel;
import com.auto.model.User;
import com.auto.page.tadashboard.DashboardPage;
import com.auto.page.tadashboard.LoginPage;
import com.auto.page.tadashboard.PanelPage;
import com.auto.test.TestBase;
import com.auto.utils.WebDriverUltis;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.auto.utils.Constants.INVALID_USERNAME_PASSWORD;

public class PanelNegativeTest extends TestBase {

    SoftAssert softAssert = new SoftAssert();
    User user = new User();
    LoginPage loginPage = new LoginPage();

    @Test(description = "Verify that user is unable to create new panel when (*) required field is not filled")
    public void DA_PANEL_TC029_User_is_unable_to_create_new_panel_when_required_field_is_not_filled() {
        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.clickPanelLnk();

        PanelPage panelPage = new PanelPage();
        panelPage.clickAddNewLink();
        panelPage.clickPanelOKButton();

        AlertMessage alertMessage = new AlertMessage("panelDisplayNameIsRequired");

       softAssert.assertTrue(panelPage.doesAlertTextDisplay(alertMessage.getText()));

    }

    @Test(description = "Verify that user is not allowed to create panel with duplicated Display Name")
    public void DA_PANEL_TC032_User_is_not_allowed_to_create_panel_with_duplicated_Display_Name() {
        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.clickPanelLnk();

        PanelPage panelPage = new PanelPage();
        panelPage.clickAddNewLink();
        Panel panel = new Panel("panelValidDisplayName");
        panelPage.createNewPanel(panel);
        WebDriverUltis.waitForPageLoad();

        panelPage.clickAddNewLink();
        panelPage.createNewPanel(panel);

        AlertMessage alertMessage = new AlertMessage("duplicatedDisplayNameAlert", panel.getDisplayName());
        softAssert.assertTrue(panelPage.doesAlertTextDisplay(alertMessage.getText()));

        panelPage.deleteAllPanels();
    }

}
