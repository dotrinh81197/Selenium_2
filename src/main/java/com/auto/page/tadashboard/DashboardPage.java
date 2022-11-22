package com.auto.page.tadashboard;

import com.auto.model.Page;
import com.auto.utils.Element;
import com.auto.utils.Utilities;
import com.auto.utils.WebDriverUltis;
import com.logigear.statics.Selaium;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

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

}


