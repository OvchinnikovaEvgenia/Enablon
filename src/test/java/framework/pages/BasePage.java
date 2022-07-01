package framework.pages;

import framework.GeneralTestSettings;
import framework.elements.BaseElement;

public class BasePage {
    private String name;
    private BaseElement baseElement;

    public BasePage(BaseElement baseElement, String name) {
        this.name = name;
        this.baseElement = baseElement;
    }

    public boolean isDisplayed() {
        GeneralTestSettings.getLogger().info(String.format("Check %s page is displayed", name));
        return baseElement.isDisplayed();
    }

    public void waitForOpen() {
        GeneralTestSettings.getLogger().info(String.format("Wait for open %s page", name));
        this.baseElement.waitForElement();
    }

    public BaseElement getBaseElement() {
        return this.baseElement;
    }
}
