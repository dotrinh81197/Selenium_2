package com.auto.page.tadashboard;

import com.auto.model.Panel;
import com.logigear.element.Element;
import io.qameta.allure.Step;

import static com.auto.utils.WebDriverUltis.waitForAlertDisplays;
import static com.logigear.statics.Selaium.driver;

public class GeneralPage {
    protected Element pageLnk = new Element("//div[@id='main-menu']//a[contains(@href,'/TADashboard') and text()= '%s']");
    protected Element choosePanelBtn = new Element("//a[@id='btnChoosepanel']");
    protected Element globalSettingLnk = new Element("//li[@class='mn-setting']/a");
    protected Element panelDisplayNameTxt = new Element("//input[@id='txtDisplayName']");
    protected Element panelChartTitleTxt = new Element("//input[@id='txtChartTitle']");
    protected Element panelCategoryCaptionTxt = new Element("//input[@id='txtCategoryXAxis']");
    protected Element panelSeriesCaptionTxt = new Element("//input[@id='txtValueYAxis']");
    protected Element panelCategoryDrl = new Element("//select[@id='cbbCategoryField']");
    protected Element panelSeriesDrl = new Element("//select[@id='cbbSeriesField']");
    protected Element panelChartTypeDrl = new Element("//select[@id='cbbChartType']");
    protected Element panelDataProfileDrl = new Element("//select[@id='cbbProfile']");
    protected Element panelStyle2DRbn = new Element("//input[@id='rdoChartStyle2D']");
    protected Element panelStyle3DRbn = new Element("//input[@id='rdoChartStyle3D']");
    protected Element panelIsShowCb = new Element("//input[@id='chkShowTitle']");
    protected Element panelDataLabelsCb = new Element("//label[contains(text(),'%s')]//input[contains(@name,'chk')]");
    protected Element panelTypeRbn = new Element("//label[@class='panel_setting_paneltype' and text()='%s']//input[@name='radPanelType']");
    protected Element panelLegendsRbn = new Element("//input[@name='radPlacement' and @value='%s']");
    protected Element panelNameLnk = new Element("//div[@class='panel_tag1']//tr//a[text()='%s']");

    @Step("Check Alert text display")
    public boolean doesAlertTextDisplay(String text) {
        waitForAlertDisplays();
        String alertText = driver().getWebDriver().switchTo().alert().getText();
        return alertText.equalsIgnoreCase(text);
    }

   
}
