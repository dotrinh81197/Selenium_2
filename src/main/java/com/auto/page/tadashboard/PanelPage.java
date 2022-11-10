package com.auto.page.tadashboard;

import com.auto.model.Panel;
import com.auto.utils.WebDriverUltis;
import com.logigear.element.Element;
import io.qameta.allure.Step;

public class PanelPage extends GeneralPage {
    private final Element addNewLnk = new Element("//a[@href=\"javascript:Dashboard.openAddPanel('');\"]");
    private final Element deleteCheckedLnk = new Element("//a[@href=\"javascript:Dashboard.deletePanels();\"]");
    private final Element checkAllLnk = new Element("//a[@href=\"javascript:Dashboard.doCheckAll(true,'chkDelPanel');\"]");
    private final Element editPanelLnk = new Element("//td/a[text()='%s']//ancestor::tr//a[text()='Edit']");
    private final Element panelOKBtn = new Element("//div[@id='div_panelPopup']//input[@id='OK']");

    public void clickAddNewLink() {
        addNewLnk.click();
    }

    public void clickPanelOKButton() {
        panelOKBtn.click();
    }

    public void deleteAllPanels() {
        checkAllLnk.click();
        deleteCheckedLnk.click();
        WebDriverUltis.acceptAlert();
    }

    public void clickEditPanel(Panel panel) {
        editPanelLnk.set(panel.getDisplayName());
        editPanelLnk.click();
    }

    @Step
    public boolean doesPanelDisplays(Panel panel) {
        panelNameLnk.set(panel.getDisplayName());
        return panelNameLnk.isDisplayed();
    }

    @Step
    public boolean clickOnPage(String pageName) {
        try {
            pageLnk.set(pageName);
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
    public boolean clickGlobalSetting() {
        try {
            globalSettingLnk.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    @Step
    public void clickPanelTypeRhn(String value) {
        panelTypeRbn.set(value);
        panelTypeRbn.click();
    }

    @Step
    public void selectDataProfileDrl(String value) {
        panelDataProfileDrl.select(value);
    }

    @Step
    public void selectChartTypeDrl(String value) {
        panelChartTypeDrl.select(value);
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
        panelCategoryDrl.select(value);
    }

    @Step
    public void selectSeriesDrl(String value) {
        panelSeriesDrl.select(value);
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
    public void createNewPanel(Panel panel) {
        if (!panel.getType().equalsIgnoreCase("")) {
            clickPanelTypeRhn(panel.getType());
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
        clickOKButton();
    }

}

