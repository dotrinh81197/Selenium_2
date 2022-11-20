package com.auto.page.tadashboard;

import com.auto.model.Page;
import com.auto.utils.Element;
import com.auto.utils.Utilities;
import com.auto.utils.WebDriverUltis;
import com.logigear.statics.Selaium;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import com.auto.model.Panel;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class DashboardPage {
    private final Element contentContainer = new Element("//div[@id='ccontent']");
    private final Element administratorLnk = new Element("//a[@href='#Welcome']");
    private final Element logoutLnk = new Element("//a[@href='logout.do']");
    private final Element repositoryLnk = new Element("//a[@href='#Repository']");
    private final Element globalSettingLnk = new Element("//li[@class='mn-setting']/a");
    private final Element addPageBtn = new Element("//a[contains(@href, 'Dashboard.openAddPageForm')]");
    private final Element newPageDialogTitleLbl = new Element("//div[@id='div_popup']//td[@class='ptc']/h2");
    private final Element pageNameTxt = new Element("//input[@class='page_txt_name']");
    private final Element OKBtn = new Element("//input[@id='OK']");
    private final Element cancelBtn = new Element("//input[@id='Cancel']");
    private final Element deletePageBtn = new Element("//a[contains(@href, 'Dashboard.doDeletePage')]");
    private final Element editPageBtn = new Element("//a[@class='edit']");
    private final Element pageParentDrl = new Element("//select[@id='parent']");
    private final Element numberColumnsDrl = new Element("//select[@id='columnnumber']");
    private final Element displayAfterDrl = new Element("//select[@id='afterpage']");
    private final Element publicCb = new Element("//input[@id='isprotected']");
    private final Element pageLnk = new Element("//div[@id='main-menu']//a[contains(@href,'/TADashboard') and text()= '%s']");
    private final Element listPageLnk = new Element("//div[@id='main-menu']//a[contains(@href,'/TADashboard')]");
    private final Element pageNoLnk = new Element("(//div[@id='main-menu']//a[contains(@href,'/TADashboard')])[%d]");
    private final Element choosePanelBtn = new Element("//a[@id='btnChoosepanel']");
    private final Element createNewPanelLnk = new Element("//span[contains(@onclick, 'Dashboard.openAddPanel')]");
    private final Element createPanelLnk = new Element("//a[contains(@onclick, 'Dashboard.openAddPanel')]");
    private final Element editPanelBtn = new Element("//li[@class='edit']");

    private final Element administerLnk = new Element("//a[@href='#Administer']");
    private final Element panelLnk = new Element("//a[@href='panels.jsp']");
    private final Element dataProfilesLnk = new Element("//a[@href='profiles.jsp']");

    private final Element panelDisplayNameTxt = new Element("//input[@id='txtDisplayName']");
    private final Element panelChartTitleTxt = new Element("//input[@id='txtChartTitle']");
    private final Element panelCategoryCaptionTxt = new Element("//input[@id='txtCategoryXAxis']");
    private final Element panelSeriesCaptionTxt = new Element("//input[@id='txtValueYAxis']");
    private final Element panelCategoryDrl = new Element("//select[@id='cbbCategoryField']");
    private final Element panelSeriesDrl = new Element("//select[@id='cbbSeriesField']");
    private final Element panelChartTypeDrl = new Element("//select[@id='cbbChartType']");
    private final Element panelDataProfileDrl = new Element("//select[@id='cbbProfile']");
    private final Element panelStyle2DRbn = new Element("//input[@id='rdoChartStyle2D']");
    private final Element panelStyle3DRbn = new Element("//input[@id='rdoChartStyle3D']");
    private final Element panelIsShowCb = new Element("//input[@id='chkShowTitle']");
    private final Element panelDataLabelsCb = new Element("//label[contains(text(),'%s')]//input[contains(@name,'chk')]");
    private final Element panelTypeRbn = new Element("//label[@class='panel_setting_paneltype' and text()='%s']//input[@name='radPanelType']");
    private final Element panelLegendsRbn = new Element("//input[@name='radPlacement' and @value='%s']");
    private final Element panelNameLnk = new Element("//div[@class='panel_tag1']//tr//a[text()='%s']");
    private final Element panelOKBtn = new Element("//div[@id='div_panelPopup']//input[@id='OK']");
    private final Element panelCancelBtn = new Element("//div[@id='div_panelPopup']//input[@id='Cancel']");
    private final Element panelConfigurationCancelBtn = new Element("//input[@onclick='Dashboard.closePanelConfigurationDlg();']");
    private final Element panelConfigurationOKBtn = new Element("//input[contains(@onclick,'Dashboard.addPanelToPage')]");
    private final Element heightTxt = new Element("//input[@id='txtHeight']");
    private final Element folderTxt = new Element("//input[@id='txtFolder']");
    private final Element panelListLnk = new Element("//div[@class='al_lft']");
    private final Element selectPageDrl = new Element("//select[@id='cbbPages']");
    private final Element displaySettingTab = new Element("//a[@href='#tabs-displaySettings']");
    private final Element filterTab = new Element("//a[@href='#tabs-data']");
    private final Element createNewPanelBtn = new Element("//div[@class='cpbutton']//span[@onclick=\"Dashboard.openAddPanel('2f9njff6y9');\"]");
    private final Element hideBtn = new Element("//span[@id='btnHidePanel']");
    private final Element preSetPanelLnk = new Element("//div[@class='pitem']//table//ul//li/a[normalize-space(contains(text(),'%s'))]");

    public String getRepositoryName() {
        return repositoryLnk.getText();
    }

    //Methods
    @Step("Verify content dashboard display")
    public boolean doesContentDisplay() {
        return contentContainer.isDisplayed();
    }

    @Step("Select repository")
    public void selectRepository(String repositoryName) {
        repositoryLnk.click();
        repositoryLnk.set(repositoryName);
        repositoryLnk.click();
    }

    @Step("Verify repository name display")
    public boolean doesRepositoryNameDisplay(String repositoryName) {
        return getRepositoryName().equalsIgnoreCase(repositoryName);
    }

    @Step("Logout dashboard page")
    public void logout() {
        administratorLnk.click();
        logoutLnk.click();
    }

    @Step
    public void clickChoosePanelBtn() {
        choosePanelBtn.click();
    }

    @Step
    public void clickCreateNewPanelBtn() {
        createNewPanelLnk.click();
    }

    @Step
    public void clickCreatePanelBtn() {
        createPanelLnk.click();
    }

    @Step
    public void clickEditPanelBtn() {
        editPanelBtn.click();
    }


    @Step
    public void clickAdministerLnk() {
        administerLnk.click();
    }

    @Step
    public void clickDataProfilesLnk() {
        clickAdministerLnk();
        dataProfilesLnk.click();
    }

    @Step
    public void clickPanelConfigurationOKBtn() {
        panelConfigurationOKBtn.click();
    }

    @Step
    public void clickPanelConfigurationCancelBtn() {
        panelConfigurationCancelBtn.click();
    }

    @Step("Check Global Setting link unclickable")
    public boolean isGlobalSettingLnkUnClickable() {
        try {
            hoverGlobalSettingLnk();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Click on Global Setting link")
    public void hoverGlobalSettingLnk() {
        globalSettingLnk.hover();
    }

    @Step("Check new page dialog title display")
    public boolean doesNewPageDialogTitleDisplay() {
        return newPageDialogTitleLbl.isDisplayed();
    }

    @Step("Enter Page name text box")
    public void enterPageNameTxt(String value) {
        pageNameTxt.enter(value);
    }

    @Step("Select Parent Page dropdown list")
    public void selectParentPageDrl(String value) {
        pageParentDrl.selectByPartOfVisibleText(value);
    }

    @Step("Select Parent Page dropdown list")
    public void selectParentPageDrl(Page page) {
        if (!page.getParentPage().equalsIgnoreCase("")) {
            selectParentPageDrl(page.getParentPage());
        }
    }

    @Step("Select Number of columns dropdown list")
    public void selectNumberOfColumnsDrl(String value) {
        numberColumnsDrl.select(value);
    }

    @Step("Select Number of columns dropdown list")
    public void selectNumberOfColumnsDrl(Page page) {
        if (!page.getNumberOfColumns().equalsIgnoreCase("")) {
            selectNumberOfColumnsDrl(page.getNumberOfColumns());
        }
    }

    @Step("Select Display after dropdown list")
    public void selectDisplayAfterDrl(String value) {
        displayAfterDrl.select(value);
    }

    @Step("Select Display after dropdown list")
    public void selectDisplayAfterDrl(Page page) {
        if (!page.getDisplayAfter().equalsIgnoreCase("")) {
            selectDisplayAfterDrl(page.getDisplayAfter());
        }
    }

    @Step("Select the public checkbox")
    public void selectPublicCb() {
        publicCb.click();
    }

    @Step("Select the public checkbox")
    public void selectPublicCb(Page page) {
        if (page.getIsPublic().equalsIgnoreCase("true")) {
            selectPublicCb();
        }
    }

    @Step("Click on OK button")
    public void clickOKBtn() {
        OKBtn.click();
    }

    @Step
    public void clickCancelBtn() {
        cancelBtn.click();
    }

    @Step("Check new page display beside a page")
    public boolean doesNewPageDisplayOnTheRight(String newPageName, String rightPageName) {
        Selaium.refresh();
        List<WebElement> listPage = listPageLnk.elements();
        Integer pageNumber = listPage.size();
        System.out.println(pageNumber);
        for (int i = 1; i < pageNumber; i++) {
            pageNoLnk.set(i);

            if (pageNoLnk.getText().equalsIgnoreCase(rightPageName)) {
                pageNoLnk.set(i + 1);
                if (pageNoLnk.getText().equalsIgnoreCase(newPageName)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Step("Remove page")
    public void removePage(String pageName) {
        WebDriverUltis.waitForPageLoad();
        clickOnPage(pageName);
        clickDeletePageBtn();
        WebDriverUltis.acceptAlert();
        WebDriverUltis.waitForPageLoad();
    }

    @Step("Remove page")
    public void removePage(ArrayList pageList) {
        for (int i = 0; i < pageList.size(); i++) {
            clickOnPage(pageList.get(i).toString());
            clickDeletePageBtn();
            WebDriverUltis.acceptAlert();
            WebDriverUltis.waitForPageLoad();
        }
    }

    @Step("Remove page")
    public void removeAllPage() {
        List<WebElement> listPage = listPageLnk.elements();
        Integer pageNumber = listPage.size();

        for (int i = pageNumber - 1; i > 1; i--) {
            pageNoLnk.set(i);
            WebDriverUltis.forceClick(pageNoLnk.element());
            clickDeletePageBtn();
            WebDriverUltis.acceptAlert();
            WebDriverUltis.waitForPageLoad();

        }
    }

    @Step("Click on page name")
    public void clickOnPage(String pageName) {
        pageLnk.set(pageName);
        WebDriverUltis.forceClick(pageLnk.element());
        WebDriverUltis.waitForPageLoad();
    }

    @Step("Click on page name")
    public void clickOnChildrenPage(Page childrenPage, Page parentPage) {

        if (!childrenPage.getParentPage().equalsIgnoreCase("")) {
            if (!parentPage.getParentPage().equalsIgnoreCase("")) {
                pageLnk.set(parentPage.getParentPage().trim());
                pageLnk.hover();
            }
            pageLnk.set(childrenPage.getParentPage().trim());
            pageLnk.hover();
        }
        pageLnk.set(childrenPage.getPageName());
        pageLnk.click();

    }

    @Step("Click Delete button")
    public void clickDeletePageBtn() {
        hoverGlobalSettingLnk();
        deletePageBtn.click();
    }

    @Step("Click Add Page button")
    public void clickAddPageBtn() {
        hoverGlobalSettingLnk();
        addPageBtn.click();
    }

    @Step
    public void clickEditPage() {
        WebDriverUltis.waitForPageLoad();
        hoverGlobalSettingLnk();
        editPageBtn.click();
    }


    @Step("Create a new page")
    public void createNewPage(Page page) {
        clickAddPageBtn();
        enterPageNameTxt(page.getPageName());
        selectParentPageDrl(page);
        selectNumberOfColumnsDrl(page);
        selectDisplayAfterDrl(page);
        selectPublicCb(page);
        clickOKBtn();
        WebDriverUltis.waitForPageLoad();
    }

    @Step("Create a new page")
    public void createNewPage(String pageName) {
        clickAddPageBtn();
        enterPageNameTxt(pageName);
        clickOKBtn();
        WebDriverUltis.waitForPageLoad();
    }

    @Step("Remove Children Page")
    public void removeChildrenPage(Page childrenPage, Page parentPage) {
        clickOnChildrenPage(childrenPage, parentPage);
        clickDeletePageBtn();
        WebDriverUltis.acceptAlert();
    }

    @Step("Create a new page")
    public void createNewPage(String pageName, String parentName) {
        clickAddPageBtn();
        enterPageNameTxt(pageName);
        selectParentPageDrl(parentName);
        clickOKBtn();
    }

    @Step("Check Page is not display")
    public boolean doesPageNotDisplay(Page page) {
        page.getPageName();
        pageLnk.set(page.getPageName());
        return Utilities.doesElementIsNotDisplay(pageLnk);
    }

    @Step("Check button delete is not display")
    public boolean doesDeleteButtonIsNotDisplay() {
        return Utilities.doesElementIsNotDisplay(deletePageBtn);
    }

    @Step
    public boolean doesNewPageDisplay(Page page, Page parentPage) {
        clickOnChildrenPage(page, parentPage);
        pageLnk.set(page.getPageName().trim());
        return pageLnk.isEnabled();

    }

    @Step
    public void editPageName(Page page, String newPageName) {
        WebDriverUltis.waitForPageLoad();
        clickOnPage(page.getPageName());
        clickEditPage();
        page.setPageName(newPageName);
        pageNameTxt.enter(page.getPageName());
        OKBtn.click();
    }

    @Step
    public void editDisplayAfter(Page page, String displayAfter) {
        WebDriverUltis.waitForPageLoad();
        clickOnPage(page.getPageName());
        clickEditPage();
        WebDriverUltis.waitForPageLoad();
        selectDisplayAfterDrl(displayAfter);
        OKBtn.click();
    }

    @Step
    public boolean doesNewPageDisplay(Page page) {
        WebDriverUltis.waitForPageLoad();
        List<WebElement> listPage = listPageLnk.elements();
        Integer pageNumber = listPage.size();
        for (int i = 1; i <= pageNumber; i++) {
            pageNoLnk.set(i);
            if (pageNoLnk.getText().equalsIgnoreCase(page.getPageName())) {
                return true;
            }
        }
        return false;

    }

    @Step
    public boolean doesNewPageDisplay(String pageName) {
        WebDriverUltis.waitForPageLoad();
        clickOnPage(pageName);
       return WebDriverUltis.getCurrentTitlePage().contains(pageName);
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
    public void clickPanelLnk() {
        clickAdministerLnk();
        panelLnk.click();
    }

    @Step
    public void createNewPanel(Panel panel) {
        if (!panel.getType().equalsIgnoreCase("")) {
            panelTypeRbn.set(panel.getType());
            panelTypeRbn.click();
        }
        if (!panel.getDataProfile().equalsIgnoreCase("")) {
            panelDataProfileDrl.select(panel.getDataProfile());
        }
        if (!panel.getDisplayName().equalsIgnoreCase("")) {
            panelDisplayNameTxt.enter(panel.getDisplayName());
        }
        if (!panel.getChartTitle().equalsIgnoreCase("")) {
            panelChartTitleTxt.enter(panel.getChartTitle());
        }
        if (!panel.getIsShowTitle().equalsIgnoreCase("") && panel.getIsShowTitle().equalsIgnoreCase("true")) {
            panelIsShowCb.click();
        }
        if (!panel.getChartType().equalsIgnoreCase("")) {
            panelChartTypeDrl.select(panel.getChartType());
        }
        if (!panel.getStyle().equalsIgnoreCase("")) {
            if (panel.getStyle().equalsIgnoreCase("2D")) {
                panelStyle2DRbn.click();
            } else if (panel.getStyle().equalsIgnoreCase("3D")) {
                panelStyle3DRbn.click();
            }
        }
        if (!panel.getCategory().equalsIgnoreCase("")) {
            panelCategoryDrl.select(panel.getCategory());
        }
        if (!panel.getCategoryCaption().equalsIgnoreCase("")) {
            panelCategoryCaptionTxt.enter(panel.getCategoryCaption());
        }
        if (!panel.getSeries().equalsIgnoreCase("")) {
            panelSeriesDrl.select(panel.getSeries());
        }
        if (!panel.getSeriesCaption().equalsIgnoreCase("")) {
            panelSeriesCaptionTxt.enter(panel.getSeriesCaption());
        }
        if (!panel.getLegends().equalsIgnoreCase("")) {
            panelLegendsRbn.set(panel.getLegends());
        }
        if (!panel.getDataLabels().equalsIgnoreCase("")) {
            String[] labels;
            labels = panel.getDataLabels().split(",");
            for (String temp : labels) {
                panelDataLabelsCb.click();
            }
        }
        panelOKBtn.click();
    }

    @Step
    public boolean doesItemDisplayCorrectly(Hashtable<String, String> data) {
        int countItem = Integer.parseInt(data.get("ItemNumber"));
        for (int i = 1; i <= countItem; i++) {
            preSetPanelLnk.set(data.get("ItemNumber" + i));
            if(!preSetPanelLnk.isDisplayed()){
                return false;
            }
        }
        return true;
    }

}


