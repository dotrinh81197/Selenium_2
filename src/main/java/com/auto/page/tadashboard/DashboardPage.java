package com.auto.page.tadashboard;

import com.auto.model.Repository;
import com.logigear.element.Element;
import io.qameta.allure.Step;

public class DashboardPage {
    private final Element contentContainer = new Element("//div[@id='ccontent']");
    private final Element administratorLnk = new Element("//a[@href='#Welcome']");
    private final Element logoutLnk = new Element("//a[@href='logout.do']");
    private final Element repositoryLnk = new Element("//a[@href='#Repository']");
    private String repositoryList = "//ul[@id='ulListRepositories']//li//a[text()='%s']";

    public String getRepositoryName() {
        return repositoryLnk.getText();
    }

    //Methods
    @Step("Verify content dashboard display")
    public boolean doesContentDisplay() {
        return contentContainer.isDisplayed();
    }

    @Step("Select repository")
    public void selectRepository(Repository repository) {
        repositoryLnk.click();
        new Element((String.format(repositoryList, repository.getRepositoryName()))).click();
    }

    @Step("Verify repository name display")
    public boolean doesRepositoryNameDisplay(Repository repository) {
        return getRepositoryName().equalsIgnoreCase(repository.getRepositoryName());
    }

    @Step("Logout dashboard page")
    public void logout() {
        administratorLnk.click();
        logoutLnk.click();
    }
}
