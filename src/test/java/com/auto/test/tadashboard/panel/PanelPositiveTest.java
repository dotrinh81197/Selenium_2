package com.auto.test.tadashboard.panel;

import com.auto.model.AlertMessage;
import com.auto.model.Page;
import com.auto.model.Panel;
import com.auto.model.User;
import com.auto.page.tadashboard.DashboardPage;
import com.auto.page.tadashboard.DataProfilesPage;
import com.auto.page.tadashboard.LoginPage;
import com.auto.page.tadashboard.PanelPage;
import com.auto.test.TestBase;
import com.auto.utils.JsonUtils;
import com.auto.utils.Utilities;
import com.auto.utils.WebDriverUltis;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public class PanelPositiveTest extends TestBase {

    SoftAssert softAssert = new SoftAssert();
    User user = new User();
    LoginPage loginPage = new LoginPage();


    @Test(dataProvider = "getDataItemPanelAvailable", dataProviderClass = JsonUtils.class, description = "Verify that when Choose panels form is expanded all pre-set panels are populated and sorted correctly")
    public void DA_PANEL_TC027_Choose_Panel_form_expanded_all_pre_set_and_sorted_correctly(Hashtable<String, String> data) {
        loginPage.login(user);
        DashboardPage dashboardPage = new DashboardPage();
        Page newPage = new Page();
        dashboardPage.createNewPage(newPage);
        Panel validDisplayNamePanel = new Panel("panelValidDisplayName");
        dashboardPage.hoverGlobalSettingLnk();
        dashboardPage.clickCreatePanelBtn();
        dashboardPage.createNewPanel(validDisplayNamePanel);
        dashboardPage.clickPanelConfigurationOKBtn();
        dashboardPage.clickChoosePanelBtn();

        softAssert.assertTrue(dashboardPage.doesItemDisplayCorrectly(data));
        dashboardPage.removePage(newPage.getPageName());


    }

    @Test(description = "Verify that when Add New Panel form is on focused all other control/form is disabled or locked")
    public void DA_PANEL_TC028_When_Add_New_Panel_form_is_on_focused_all_other_control_form_is_disabled_or_locked() {
        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.gotoPanelPage();

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
        dashboardPage.gotoPanelPage();

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
        dashboardPage.gotoPanelPage();

        PanelPage panelPage = new PanelPage();
        panelPage.clickAddNewLink();
        Panel invalidPanel = new Panel("panelInvalidDisplayName");
        panelPage.createNewPanel(invalidPanel);

        AlertMessage alertMessage = new AlertMessage("panelDisplayNameIsRequired");
        panelPage.doesAlertTextDisplay(alertMessage.getText());

        WebDriverUltis.acceptAlert();
        panelPage.closePanelModal();
        panelPage.clickAddNewLink();
        Panel panel = new Panel("panelValidDisplayName");
        panelPage.createNewPanel(panel);
        softAssert.assertTrue(panelPage.doesPanelDisplays(panel));

        panelPage.deleteAllPanels();

    }

    @Test(description = "Verify that correct panel setting form is displayed with corresponding panel type selected")
    public void DA_PANEL_TC031_Panel_Setting_Form_Display_Correctly_With_Corresponding_Panel_Type_Selected() {
        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.gotoPanelPage();

        PanelPage panelPage = new PanelPage();
        panelPage.clickAddNewLink();
        softAssert.assertFalse(panelPage.doesSettingFormDisplay("Chart Settings"));
        panelPage.selectPanelTypeRbn("Indicator");
        softAssert.assertFalse(panelPage.doesSettingFormDisplay("Indicator Settings"));
        panelPage.selectPanelTypeRbn("Heat Map");
        softAssert.assertFalse(panelPage.doesSettingFormDisplay("Heat Map Settings"));

    }

    @Test(description = "Verify that Data Profile listing of Add New Panel and Edit Panel control/form are in alphabetical order")
    public void DA_PANEL_TC033_Data_Profile_listing_of_Add_New_Panel_and_Edit_Panel_control_form_are_in_alphabetical_order() {
        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.gotoPanelPage();

        PanelPage panelPage = new PanelPage();
        panelPage.clickAddNewLink();
        softAssert.assertTrue(panelPage.doesDataProfileListDisplaysAlphabeticalOrder());

        Panel panel = new Panel("panelValidDisplayName");
        panelPage.createNewPanel(panel);

        panelPage.clickEditPanel(panel);
        softAssert.assertTrue(panelPage.doesDataProfileListDisplaysAlphabeticalOrder());

        panelPage.deleteAllPanels();
    }

    @Test(description = "Verify that newly created data profiles are populated correctly under the Data Profile dropped down menu in  Add New Panel and Edit Panel control/form")
    public void DA_PANEL_TC034_Data_Profile_listing_of_Add_New_Panel_and_Edit_Panel_control_form_are_in_alphabetical_order() {
        loginPage.login(user);
        String dataProfileName = "giang-data";
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.clickDataProfilesLnk();

        DataProfilesPage dataProfilesPage = new DataProfilesPage();
        dataProfilesPage.createDataProfile(dataProfileName);
        WebDriverUltis.waitForPageLoad();

        dashboardPage.gotoPanelPage();
        PanelPage panelPage = new PanelPage();
        panelPage.clickAddNewLink();

        softAssert.assertTrue(panelPage.doesDataProfileContainIntoDrl(dataProfileName));
        softAssert.assertTrue(panelPage.doesDataProfileListDisplaysAlphabeticalOrder());

        panelPage.clickPanelCancelBtn();

        dataProfilesPage.deleteAllDataProfileCreated();
    }

    @Test(description = "Verify that no special character except '@' character is allowed to be inputted into Chart Title field")
    public void DA_PANEL_TC035_No_Special_Character_Except_At_Character_Is_Allowed_Into_Chart_Title_Field() {
        loginPage.login(user);
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.gotoPanelPage();

        PanelPage panelPage = new PanelPage();
        Panel invalidPanel = new Panel("panelInvalidDisplayName");
        panelPage.createNewPanel(invalidPanel);

        AlertMessage alertMessage = new AlertMessage("panelDisplayNameIsNotValid");
        String actualAlertMessage = Utilities.getAlertMessage();
        String expectedMessage = alertMessage.getAlertText();
        softAssert.assertEquals(actualAlertMessage, expectedMessage, "Verify alert message " + expectedMessage + "display");

        WebDriverUltis.acceptAlert();
        panelPage.clickAddNewLink();
        Panel validPanel = new Panel("panelValidDisplayName");
        panelPage.createNewPanel(validPanel);
        WebDriverUltis.waitForPageLoad();
        softAssert.assertTrue(panelPage.doesPanelDisplays(validPanel));

        panelPage.deleteAllPanels();

    }

    @Test(description = "Verify that all chart types ( Pie, Single Bar, Stacked Bar, Group Bar, Line ) are listed correctly under Chart Type dropped down menu")
    public void DA_PANEL_TC036_All_chart_types_Pie_Single_Bar_Stacked_Bar_Group_Bar_Line_are_listed_correctly_under_Chart_Type_dropped_down_menu() {
        loginPage.login(user);
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.gotoPanelPage();
        WebDriverUltis.waitForPageLoad();
        PanelPage panelPage = new PanelPage();
        panelPage.clickAddNewLink();
        ArrayList<String> chartTypeList = new ArrayList<String>(Arrays.asList("Pie", "Single Bar", "Stacked Bar", "Group Bar", "Line"));
        softAssert.assertEquals(panelPage.getChartTypeList(), chartTypeList);

    }

    @Test(description = "Verify that Category, Series and Caption field are enabled and disabled correctly corresponding to each type of the Chart Type", dataProviderClass = JsonUtils.class, dataProvider = "getDataChartType")
    public void DA_PANEL_TC037_Category_Series_Caption_Field_Enabled_Disabled_Correctly_To_Each_Chart_Type(Hashtable<String, String> data) {
        loginPage.login(user);
        DashboardPage dashboardPage = new DashboardPage();
        Page validPage = new Page();
        dashboardPage.createNewPage(validPage);
        WebDriverUltis.waitForPageLoad();
        dashboardPage.clickChoosePanelBtn();
        dashboardPage.clickCreateNewPanelBtn();

        dashboardPage.selectChartTypeDrl(data.get("ChartType"));

        softAssert.assertEquals(dashboardPage.doesCategoriesDisable(), data.get("CategoryFieldDisable"));
        softAssert.assertEquals(dashboardPage.doesSeriesDisable(), data.get("SeriesFieldDisable"));
        softAssert.assertEquals(dashboardPage.doesCategoriesCaptionDisable(), data.get("CategoryCaptionFieldDisable"));
        softAssert.assertEquals(dashboardPage.doesSeriesCaptionDisable(), data.get("SeriesCaptionFieldDisable"));

        dashboardPage.closePanelModal();
        dashboardPage.removePage(validPage.getPageName());

    }

    @Test(description = "Verify that all settings within Add New Panel and Edit Panel form stay unchanged when user switches between 2D and 3D radio buttons")
    public void DA_PANEL_TC038_All_Setting_Add_New_Panel_And_Edit_Panel_Stay_Unchanged_When_Switches_Between_2D_3D() {
        loginPage.login(user);
        DashboardPage dashboardPage = new DashboardPage();
        Page validPage = new Page();
        dashboardPage.createNewPage(validPage);
        dashboardPage.clickChoosePanelBtn();
        dashboardPage.clickCreateNewPanelBtn();
        WebDriverUltis.waitForPageLoad();
        Panel panel3D = new Panel("panel3D");
        dashboardPage.fillPanelInfo(panel3D);

        softAssert.assertTrue(dashboardPage.doesChartTypeUnChanges(panel3D.getChartType()));
        softAssert.assertTrue(dashboardPage.doesDataProfileUnChanges(panel3D.getDataProfile()));
        softAssert.assertTrue(dashboardPage.doesDisplayNameUnChanges(panel3D.getDisplayName()));
        softAssert.assertTrue(dashboardPage.doesChartTitleUnChanges(panel3D.getChartTitle()));
        softAssert.assertEquals(dashboardPage.getShowTitle(), panel3D.getIsShowTitle());

        dashboardPage.clickStyle2DRbn();
        softAssert.assertTrue(dashboardPage.doesChartTypeUnChanges(panel3D.getChartType()));
        softAssert.assertTrue(dashboardPage.doesDataProfileUnChanges(panel3D.getDataProfile()));
        softAssert.assertTrue(dashboardPage.doesDisplayNameUnChanges(panel3D.getDisplayName()));
        softAssert.assertTrue(dashboardPage.doesChartTitleUnChanges(panel3D.getChartTitle()));
        softAssert.assertEquals(dashboardPage.getShowTitle(), panel3D.getIsShowTitle());

        dashboardPage.clickOKButton();
        dashboardPage.selectPagePanelConfig(validPage.getPageName());
        dashboardPage.clickPanelConfigurationOKBtn();
        dashboardPage.clickEditPanelBtn();

        softAssert.assertTrue(dashboardPage.doesChartTypeUnChanges(panel3D.getChartType()));
        softAssert.assertTrue(dashboardPage.doesDataProfileUnChanges(panel3D.getDataProfile()));
        softAssert.assertTrue(dashboardPage.doesDisplayNameUnChanges(panel3D.getDisplayName()));
        softAssert.assertTrue(dashboardPage.doesChartTitleUnChanges(panel3D.getChartTitle()));
        softAssert.assertEquals(dashboardPage.getShowTitle(), panel3D.getIsShowTitle());

        dashboardPage.closePanelModal();
        dashboardPage.removePage(validPage.getPageName());
        WebDriverUltis.waitForPageLoad();
        dashboardPage.gotoPanelPage();
        PanelPage panelPage = new PanelPage();
        panelPage.deleteAllPanels();

    }

    @Test(dataProvider = "getLegendType", dataProviderClass = JsonUtils.class, description = "Verify that all settings within 'Add New Panel' and 'Edit Panel' form stay unchanged when user switches between 'Legends' radio buttons")
    public void DA_PANEL_TC039_All_Setting_Form_Stay_Unchanged_When_Switch_Legends(Hashtable<String, String> data) {
        loginPage.login(user);
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.gotoPanelPage();
        PanelPage panelPage = new PanelPage();

        panelPage.clickAddNewLink();
        panelPage.selectLegendRbn(data.get("legendType"));

        Panel defaultPanel = new Panel();
        panelPage.doesPanelDefaultFormDisplayUnchanged(defaultPanel);

        panelPage.closePanelModal();

        Panel panel = new Panel("panelValidDisplayName");
        panelPage.clickAddNewLink();
        panelPage.createNewPanel(panel);

        panelPage.clickEditPanel(panel);
        panelPage.selectLegendRbn(data.get("legendType"));
        panelPage.doesPanelFormDisplayUnchanged(panel);
        panelPage.closePanelModal();
        panelPage.deleteAllPanels();

    }

    @Test(dataProvider = "getDataLabels", dataProviderClass = JsonUtils.class, description = "Verify that all 'Data Labels' check boxes are enabled and disabled correctly corresponding to each type of 'Chart Type'")
    public void DA_PANEL_TC040_Data_Labels_Display_Correctly(Hashtable<String, String> data) {
        loginPage.login(user);
        DashboardPage dashboardPage = new DashboardPage();
        Page validPage = new Page();
        dashboardPage.createNewPage(validPage);
        dashboardPage.clickChoosePanelBtn();
        dashboardPage.clickCreateNewPanelBtn();
        WebDriverUltis.waitForPageLoad();

        dashboardPage.selectChartTypeDrl(data.get("ChartType"));
        softAssert.assertEquals(dashboardPage.doesDataLabelCbDisable("Series"), data.get("DataLabelSeriesDisable"));
        softAssert.assertEquals(dashboardPage.doesDataLabelCbDisable("Categories"), data.get("DataLabelCategoriesDisable"));
        softAssert.assertEquals(dashboardPage.doesDataLabelCbDisable("Value"), data.get("DataLabelValueDisable"));
        softAssert.assertEquals(dashboardPage.doesDataLabelCbDisable("Percentage"), data.get("DataLabelPercentageDisable"));

        dashboardPage.closePanelModal();
        dashboardPage.removePage(validPage.getPageName());

    }

    @Test(dataProvider = "getDataLabelsType", dataProviderClass = JsonUtils.class, description = "Verify that all 'Data Labels' check boxes are enabled and disabled correctly corresponding to each type of 'Chart Type'")
    public void DA_PANEL_TC041_Data_Labels_Display_Correctly(Hashtable<String, String> data) {
        loginPage.login(user);
        DashboardPage dashboardPage = new DashboardPage();

        dashboardPage.gotoPanelPage();
        PanelPage panelPage = new PanelPage();
        panelPage.clickAddNewLink();
        WebDriverUltis.waitForPageLoad();
        Panel defaultPanel = new Panel();
        panelPage.selectPanelDataLabelCb(data.get("dataLabelType"));
        softAssert.assertTrue(dashboardPage.doesPanelDefaultFormDisplayUnchanged(defaultPanel));
        dashboardPage.selectPanelDataLabelCb(data.get("dataLabelType"));

        dashboardPage.closePanelModal();

    }

    @Test(description = "Verify that all pages are listed correctly under the Select page dropped down menu of Panel Configuration form/ control")
    public void DA_PANEL_TC042_All_pages_are_listed_correctly_under_the_Select_page_dropped_down_menu_of_Panel_Configuration_form_control() {
        loginPage.login(user);
        DashboardPage dashboardPage = new DashboardPage();
        String newPage1 = "page1";
        String newPage2 = "page2";
        String newPage3 = "page3";
        dashboardPage.createNewPage(newPage1);

        dashboardPage.createNewPage(newPage2);

        dashboardPage.createNewPage(newPage3);

        dashboardPage.clickChoosePanelBtn();
        dashboardPage.clickCreateNewPanelBtn();
        Panel panel = new Panel("panelValidDisplayName");
        dashboardPage.createNewPanel(panel);
        ArrayList<String> selectPageList = new ArrayList<String>(Arrays.asList(newPage1, newPage2, newPage3));
        softAssert.assertEquals(dashboardPage.getSelectPageList(), selectPageList);

        dashboardPage.clickPanelConfigurationCancelBtn();
        dashboardPage.removePage(selectPageList);
        dashboardPage.gotoPanelPage();
        PanelPage panelPage = new PanelPage();
        panelPage.deleteAllPanels();

    }


}
