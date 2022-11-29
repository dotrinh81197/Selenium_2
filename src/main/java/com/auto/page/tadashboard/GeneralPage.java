package com.auto.page.tadashboard;

import com.auto.model.Page;
import com.auto.model.Panel;
import com.auto.utils.Element;
import com.auto.utils.WebDriverUltis;
import com.google.common.collect.Ordering;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.auto.utils.Utilities.doesElementDisable;
import static com.auto.utils.WebDriverUltis.waitForAlertDisplays;
import static com.logigear.statics.Selaium.driver;

public class GeneralPage {
    private final Element administerLnk = new Element("//a[@href='#Administer']");
    private final Element panelLnk = new Element("//a[@href='panels.jsp']");
    private final Element dataProfilesLnk = new Element("//a[@href='profiles.jsp']");

    protected Element pageLnk = new Element("//div[@id='main-menu']//a[contains(@href,'/TADashboard') and text()= '%s']");
    protected Element choosePanelBtn = new Element("//a[@id='btnChoosepanel']");
    protected Element globalSettingLnk = new Element("//li[@class='mn-setting']/a");
    protected Element panelDisplayNameTxt = new Element("//input[@id='txtDisplayName']");
    protected Element panelChartTitleTxt = new Element("//input[@id='txtChartTitle']");
    protected Element panelCategoryCaptionTxt = new Element("//input[@id='txtCategoryXAxis']");
    protected com.auto.utils.Element panelSeriesCaptionTxt = new Element("//input[@id='txtValueYAxis']");
    protected Element panelCategoryDrl = new Element("//select[@id='cbbCategoryField']");
    protected Element panelSeriesDrl = new Element("//select[@id='cbbSeriesField']");
    protected Element panelChartTypeDrl = new Element("//select[@id='cbbChartType']");
    protected Element panelDataProfileDrl = new Element("//select[@id='cbbProfile']");
    protected Element panelStyle2DRbn = new Element("//input[@id='rdoChartStyle2D']");
    protected Element panelStyle3DRbn = new Element("//input[@id='rdoChartStyle3D']");
    protected Element panelIsShowCb = new Element("//input[@id='chkShowTitle']");
    protected Element panelDataLabelsCb = new Element("//label[contains(text(),'%s')]//input[contains(@name,'chk')]");
    protected Element panelTypeRbn = new Element("//label[@class='panel_setting_paneltype' and contains(text(),'%s')]//input[@name='radPanelType']");
    protected Element panelTypeLbl = new Element("//label[@class='panel_setting_paneltype' and contains(text(),'%s')]");
    protected Element panelLegendsRbn = new Element("//input[@name='radPlacement' and @value='%s']");
    protected Element panelNameLnk = new Element("//div[@class='panel_tag1']//tr//a[text()='%s']");
    protected Element panelCancelBtn = new Element("//input[@id='Cancel']");
    protected Element panelOKBtn = new Element("//div[@id='div_panelPopup']//input[@id='OK']");
    protected Element selectPageDrl = new Element("//select[@id='cbbPages']");
    protected Element dataLabelsCb = new Element("//td[@class='general_vertical_top' and text()='Data Labels']//following-sibling::td//input");
    protected Element heightTxt = new Element("//input[@id='txtHeight']");
    protected Element folderTxt = new Element("//input[@id='txtFolder']");
    protected Element panelConfigurationOKBtn = new Element("//input[contains(@onclick,'Dashboard.addPanelToPage')]");
    protected Element closeModalBtn = new Element("//a[@class='ui-dialog-titlebar-close']");


    @Step("Check Alert text display")
    public boolean doesAlertTextDisplay(String text) {
        waitForAlertDisplays();
        String alertText = driver().getWebDriver().switchTo().alert().getText();
        return alertText.equalsIgnoreCase(text);
    }

    @Step
    public void clickAdministerLnk() {
        administerLnk.click();
    }

    @Step
    public void closeModalBtn() {
        closeModalBtn.click();
    }

    @Step
    public void gotoPanelPage() {
        clickAdministerLnk();
        panelLnk.click();
    }

    @Step
    public void clickDataProfilesLnk() {
        clickAdministerLnk();
        dataProfilesLnk.click();
    }

    @Step
    public void clickPanelOkBtn() {
        panelOKBtn.click();
    }

    @Step
    public void clickPanelCancelBtn() {
        panelCancelBtn.click();
    }

    @Step
    public boolean doesCategoriesDisable() {
        return doesElementDisable(panelCategoryDrl);
    }

    @Step
    public boolean doesCategoriesCaptionDisable() {
        return doesElementDisable(panelSeriesCaptionTxt);
    }

    @Step
    public boolean doesSeriesDisable() {
        return doesElementDisable(panelSeriesDrl);
    }

    @Step
    public boolean doesSeriesCaptionDisable() {
        return doesElementDisable(panelSeriesCaptionTxt);
    }

    @Step
    public boolean doesDataLabelCbDisable(String value) {
        panelDataLabelsCb.set(value);
        return doesElementDisable(panelDataLabelsCb);
    }

    @Step
    public void closePanelModal() {
        panelCancelBtn.click();
    }

    @Step
    public void selectChartTypeDrl(String value) {
        panelChartTypeDrl.selectByPartOfVisibleText(value);
    }

    @Step
    public void selectPagePanelConfig(String value) {
        selectPageDrl.selectByPartOfVisibleText(value);
    }

    @Step
    public void selectLegendRbn(String value) {
        panelLegendsRbn.set(value);
        panelLegendsRbn.click();
    }

    @Step
    public void selectPanelDataLabelCb(String value) {
        panelDataLabelsCb.set(value);
        panelDataLabelsCb.click();
    }


    @Step
    public void createNewPanel(Panel panel) {
        fillPanelInfo(panel);
        clickOKButton();
    }

    @Step
    public void fillPanelInfo(Panel panel) {
        if (!panel.getType().equalsIgnoreCase("")) {
            selectPanelTypeRbn(panel.getType());
        }
        if (!panel.getDataProfile().equalsIgnoreCase("")) {
            selectDataProfileDrl(panel.getDataProfile());
        }
        if (!panel.getDisplayName().equalsIgnoreCase("")) {
            enterDisplayNameTxt(panel.getDisplayName());
        }
        if (!panel.getChartTitle().equalsIgnoreCase("")) {
            enterChartTitleTxt(panel.getChartTitle());
        }
        if (!panel.getIsShowTitle().equalsIgnoreCase("") && panel.getIsShowTitle().equalsIgnoreCase("true")) {
            clickIsShowCb();
        }
        if (!panel.getChartType().equalsIgnoreCase("")) {
            selectChartTypeDrl(panel.getChartType());
        }
        if (!panel.getStyle().equalsIgnoreCase("")) {
            if (panel.getStyle().equalsIgnoreCase("2D")) {
                clickStyle2DRbn();
            } else if (panel.getStyle().equalsIgnoreCase("3D")) {
                clickStyle3DRbn();
            }
        }
        if (!panel.getCategory().equalsIgnoreCase("")) {
            selectCategoryDrl(panel.getCategory());
        }
        if (!panel.getCategoryCaption().equalsIgnoreCase("")) {
            enterCategoryCaptionTxt(panel.getCategoryCaption());
        }
        if (!panel.getSeries().equalsIgnoreCase("")) {
            selectSeriesDrl(panel.getSeries());
        }
        if (!panel.getSeriesCaption().equalsIgnoreCase("")) {
            enterSeriesCaptionTxt(panel.getSeriesCaption());
        }
        if (!panel.getLegends().equalsIgnoreCase("")) {
            clickLegendsRbn(panel.getLegends());
        }
        if (!panel.getDataLabels().equalsIgnoreCase("")) {
            clickDataLabelsCb(panel.getDataLabels());
        }

    }

    @Step
    public void selectPanelTypeRbn(String value) {
        panelTypeRbn.set(value);
        panelTypeRbn.click();
    }

    @Step
    public void selectDataProfileDrl(String value) {
        panelDataProfileDrl.selectByPartOfVisibleText(value);
    }

    @Step
    public void enterDisplayNameTxt(String value) {
        panelDisplayNameTxt.enter(value);
    }

    @Step
    public void enterChartTitleTxt(String value) {
        panelChartTitleTxt.enter(value);
    }

    @Step
    public void enterCategoryCaptionTxt(String value) {
        panelCategoryCaptionTxt.enter(value);
    }

    @Step
    public void enterSeriesCaptionTxt(String value) {
        panelSeriesCaptionTxt.enter(value);
    }

    @Step
    public void clickIsShowCb() {
        panelIsShowCb.click();
    }

    @Step
    public void clickStyle2DRbn() {
        panelStyle2DRbn.click();
    }

    @Step
    public void clickStyle3DRbn() {
        panelStyle3DRbn.click();
    }

    @Step
    public void selectCategoryDrl(String value) {
        panelCategoryDrl.selectByPartOfVisibleText(value);
    }

    @Step
    public void selectSeriesDrl(String value) {
        panelSeriesDrl.selectByPartOfVisibleText(value);
    }

    @Step
    public void clickLegendsRbn(String value) {
        panelLegendsRbn.set(value);
        panelLegendsRbn.click();
    }

    @Step
    public void clickDataLabelsCb(String value) {
        panelDataLabelsCb.set(value);
        panelDataLabelsCb.click();
    }

    @Step
    public void clickOKButton() {
        panelOKBtn.click();
    }

    @Step
    public boolean clickOnPage(Page page) {
        try {
            page.getPageName();
            pageLnk.set(page.getPageName());
            WebDriverUltis.forceClick(pageLnk.element());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step
    public boolean clickChoosePanelButton() {
        try {
            choosePanelBtn.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step
    public void enterHeightField(String value) {
        heightTxt.enter(value);
        panelConfigurationOKBtn.click();
    }

    @Step
    public void enterFolderField(String value) {
        folderTxt.enter(value);
        panelConfigurationOKBtn.click();
    }


    @Step
    public boolean doesDataProfileListDisplaysAlphabeticalOrder() {
        ArrayList<String> listOfSelection = new ArrayList<>();
        try {
            List<String> listSelection = panelDataProfileDrl.getOptions();

            for (String temp : listSelection) {
                listOfSelection.add(temp);
            }
            return Ordering.natural().isOrdered(listOfSelection);
        } catch (Exception e) {
            Allure.description("Can't find Dropdown list");
            return false;
        }
    }

    @Step
    public boolean doesDataProfileContainIntoDrl(String value) {
        try {
            List<String> listSelection = panelDataProfileDrl.getOptions();
            return listSelection.contains(value);

        } catch (Exception e) {
            Allure.description("Can't find" + value + " Dropdown list");
            return false;
        }
    }

    @Step
    public ArrayList<String> getChartTypeList() {
        return (ArrayList<String>) panelChartTypeDrl.getOptions();
    }

    @Step
    public ArrayList<String> getSelectPageList() {
        return (ArrayList<String>) selectPageDrl.getOptions();
    }

    @Step
    public String getChartTypeValue() {
        return panelChartTypeDrl.getValue();
    }

    @Step
    public String getDataProfileValue() {
        return panelDataProfileDrl.getValue();
    }

    @Step
    public String getDisplayNameValue() {
        return panelDisplayNameTxt.getValue();
    }

    @Step
    public String getChartTitleValue() {
        return panelChartTypeDrl.getValue();
    }

    @Step
    public boolean getShowTitle() {
        return panelIsShowCb.isChecked();
    }


    @Step
    public boolean doesValueFieldUnChanges(String value, Element element) {
        try {
            return element.getValue().equalsIgnoreCase(value);

        } catch (Exception e) {
            return false;
        }
    }

    @Step
    public boolean doesDropdownFieldUnChanges(String value, Element element) {
        try {
            return element.getSelected().equalsIgnoreCase(value);

        } catch (Exception e) {
            return false;
        }
    }

    @Step
    public boolean doesTypeUnChanges(String value) {
        panelTypeRbn.set(value);
        return panelTypeRbn.isSelected();
    }

    @Step
    public boolean doesLegendUnChanges(String value) {
        panelTypeRbn.set(value);
        return panelTypeRbn.isChecked();
    }

    @Step
    public boolean doesChartTypeUnChanges(String value) {
        return doesValueFieldUnChanges(value, panelChartTypeDrl);
    }

    @Step
    public boolean doesDataProfileUnChanges(String value) {
        return doesDropdownFieldUnChanges(value, panelDataProfileDrl);
    }

    @Step
    public boolean doesDisplayNameUnChanges(String value) {
        return doesValueFieldUnChanges(value, panelDisplayNameTxt);
    }

    @Step
    public boolean doesChartTitleUnChanges(String value) {
        return doesValueFieldUnChanges(value, panelChartTitleTxt);
    }

    @Step
    public boolean doesCategoryUnChanges(String value) {
        if (!doesCategoriesCaptionDisable()) {
            return doesValueFieldUnChanges(value, panelCategoryDrl);
        }

        return false;
    }

    @Step
    public boolean doesDataLabelsNoneSelected() {
        List<WebElement> listDataLabelsCb = dataLabelsCb.elements();
        int count = listDataLabelsCb.size();
        int i;
        for (i = 0; i < count; i++) {
            if (listDataLabelsCb.get(i).isSelected()) {
                return false;
            }
        }
        return true;
    }

    @Step
    public boolean doesTypeLblUnchanged(String value) {
        return doesValueFieldUnChanges(value, panelTypeLbl);
    }

    @Step
    public boolean doesPanelDefaultFormDisplayUnchanged(Panel panel) {
        boolean type, dataProfile, displayName, chartTitle, charType, category, series, dataLabels;
        type = doesTypeUnChanges(panel.getType());
        dataProfile = doesDataProfileUnChanges(panel.getDataProfile());
        displayName = doesDisplayNameUnChanges(panel.getDisplayName());
        chartTitle = doesChartTitleUnChanges(panel.getChartTitle());
        charType = doesChartTypeUnChanges(panel.getChartType());
        category = doesCategoriesCaptionDisable();
        series = !doesSeriesDisable();
        dataLabels = doesDataLabelsNoneSelected();
        return type && dataProfile && displayName && chartTitle && charType && category && series && dataLabels;

    }

    @Step
    public boolean doesPanelFormDisplayUnchanged(Panel panel) {
        boolean type, dataProfile, displayName, chartTitle, charType, category, series, dataLabels;
        type = doesTypeLblUnchanged(panel.getType());
        dataProfile = doesDataProfileUnChanges(panel.getDataProfile());
        displayName = doesDisplayNameUnChanges(panel.getDisplayName());
        chartTitle = doesChartTitleUnChanges(panel.getChartTitle());
        charType = doesChartTypeUnChanges(panel.getChartType());
        if (panel.getCategory().equalsIgnoreCase("")) {
            category = doesCategoriesCaptionDisable();
        } else category = doesCategoryUnChanges(panel.getCategory());

        series = !doesSeriesCaptionDisable();
        dataLabels = doesDataLabelsNoneSelected();
        return type && dataProfile && displayName && chartTitle && charType && category && series && dataLabels;
    }


}
