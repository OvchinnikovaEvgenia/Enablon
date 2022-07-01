package framework.elements;

import framework.GeneralTestSettings;
import framework.browser.Browser;
import framework.browser.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import java.util.stream.Collectors;

public class BaseElement {

    protected By locator;
    private String name;
    private Actions actions = new Actions(BrowserFactory.getDriver());

    public BaseElement(By locator, String name) {
        this.locator = locator;
        this.name = name;
    }

    public void click() {
        GeneralTestSettings.getLogger().info(String.format("Click on %s ", name));
        this.findElement().click();
    }

    public WebElement findElement() {
        GeneralTestSettings.getLogger().info(String.format("Find element %s ", name));
        return BrowserFactory.getDriver().findElement(locator);
    }

    public void waitForElement() {
        GeneralTestSettings.getLogger().info(String.format("Wait for %s ", name));
        GeneralTestSettings.getWait().until(ExpectedConditions.presenceOfElementLocated(this.locator));
    }

    public boolean isDisplayed() {
        GeneralTestSettings.getLogger().info(String.format("Check %s is displayed", name));
        return this.findElement().isDisplayed();
    }

    public boolean isExist() {
        GeneralTestSettings.getLogger().info(String.format("Check %s is exist", name));
        List<WebElement> list = BrowserFactory.getDriver().findElements(locator);
        return list.size() != 0;
    }

    public void waitForDisappeared() {
        GeneralTestSettings.getLogger().info(String.format("Wait for %s disappeared", name));
        GeneralTestSettings.getWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void clear() {
        GeneralTestSettings.getLogger().info(String.format("Clear %s", name));
        this.findElement().clear();
    }

    public void sendKeys(CharSequence text) {
        GeneralTestSettings.getLogger().info(String.format("Send text to %s", name));
        this.findElement().sendKeys(text);
    }

    public boolean checkAttribute(String attributeName, String expectedValue) {
        return this.findElement().getAttribute(attributeName).equals(expectedValue);
    }

    public void waitForAttribute(String attributeName, String expectedValue) {
        GeneralTestSettings.getWait().until(
                ExpectedConditions
                        .attributeContains(this.locator, attributeName , expectedValue));
    }

    public void doubleClick() {
        actions.doubleClick(this.findElement()).build().perform();
    }

    public List<String> getTextFromAll() {
        return Browser.findElements(this.locator).stream().map(
                webElement -> webElement.getText())
                .sorted()
                .collect(Collectors.toList());
    }

    public String getText() {
        return BrowserFactory.getDriver().findElement(this.locator).getText();
    }

    public void moveToElement() {
        actions.moveToElement(this.findElement()).build().perform();
    }

}
