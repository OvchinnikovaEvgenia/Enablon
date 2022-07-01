package tests;

import framework.GeneralTestSettings;
import framework.browser.BrowserFactory;
import pages.TodosPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import testUtils.SettingsManager;

public class BaseTest {

    @BeforeMethod
    protected void beforeTestMethod() {
        GeneralTestSettings.getLogger().info("Initialize main settings");
        BrowserFactory.initializeBrowser();
        GeneralTestSettings.setGeneralSettings();
        BrowserFactory.getDriver().get(SettingsManager.getBaseURL());
        BrowserFactory.getDriver().manage().window().maximize();
    }

    @AfterMethod
    protected void afterTestMethod() {
        BrowserFactory.getDriver().quit();
    }

    protected TodosPage getTodosPage() {
        TodosPage page = new TodosPage("Page to add points to todo list");
        page.waitForOpen();
        Assert.assertTrue(page.isDisplayed());
        return page;
    }
}
