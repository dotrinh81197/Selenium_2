package com.auto.page.tadashboard;

import com.auto.model.Page;
import com.auto.utils.Constants;
import com.auto.utils.Element;
import com.auto.utils.Utilities;
import com.auto.utils.WebDriverUltis;

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
    private final List<WebElement> listPageLnk = new Element("//div[@id='main-menu']//a[contains(@href,'/TADashboard')]").elements();

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
        pageParentDrl.select(value);
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
    public boolean doesNewPageDisplayBeside(String newPageName, String besidePageName) {
        Integer pageNumber = listPageLnk.size();

        for (int i = 0; i < pageNumber; i++) {
            if (listPageLnk.get(i).getText().equalsIgnoreCase(besidePageName)) {
                if (listPageLnk.get(i + 1).getText().equalsIgnoreCase(newPageName)) {
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
    }

    @Step("Remove Children Page")
    public void removeChildrenPage(Page childrenPage, Page parentPage) {
        WebDriverUltis.waitForPageLoad();
        clickOnChildrenPage(childrenPage, parentPage);
        clickDeletePageBtn();
        WebDriverUltis.acceptAlert();
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
        return pageLnk.isDisplayed();

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
        Integer pageNumber = listPageLnk.size();
        for (int i = 0; i < pageNumber; i++) {
            if (listPageLnk.get(i).getText().equalsIgnoreCase(page.getPageName())) {
                return true;
            }
        }
        return false;

    }

}


