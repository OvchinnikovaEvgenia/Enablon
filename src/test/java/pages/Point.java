package pages;

import framework.GeneralTestSettings;
import framework.elements.Button;
import framework.elements.Input;
import framework.elements.Label;
import framework.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class Point extends BasePage {

    private String text;
    private Input completedInput;
    private Button destroyButton;
    private Label labelWithText;
    private Input inputToEdit;

    private Point(String locator, String name) {
        super(new Label(By.xpath(locator), "Label with text"), name);
        completedInput = new Input(By.xpath(locator + "//input[contains(@class, 'toggle')]"), "Checkbox to complete");
        destroyButton = new Button(By.xpath(locator + "//button[contains(@class, 'destroy')]"), "Button to destroy");
        labelWithText = new Label(By.xpath(locator + "//label"), "Label with text");
        inputToEdit = new Input(By.xpath("//input[contains(@class, 'edit')]"), "Input to edit");
    }

    public static Point getPointByText(String text) {
        GeneralTestSettings.getLogger().info(String.format("Create point with text %s", text));
        String locatorFromText = String.format("//label[text() = '%s']/ancestor::li", text);
        return new Point(locatorFromText, String.format("Point %s",text));
    }

    public Input getCompletedInput() {
        completedInput.waitForElement();
        return completedInput;
    }

    public boolean isCompleted() {
        GeneralTestSettings.getLogger().info("Check is point completed");
        return this.getBaseElement().checkAttribute("class", "completed");
    }

    public void waitForEditState() {
        GeneralTestSettings.getLogger().info("Wait for edit state of the point");
        this.getBaseElement().waitForAttribute("class", "editing");
    }

    public Point clearPoint() {
        GeneralTestSettings.getLogger().info("Clear point");
        this.getBaseElement().doubleClick();
        this.waitForEditState();
        this.inputToEdit.clear();
        return getPointByText("");
    }

    public Point insertTextToPoint(String newText) {
        GeneralTestSettings.getLogger().info("Insert text into point");
        this.getBaseElement().doubleClick();
        this.waitForEditState();
        this.inputToEdit.sendKeys(newText);
        this.inputToEdit.sendKeys(Keys.ENTER);
        return getPointByText(newText);
    }

    public void clickDestroyButton() {
        this.getBaseElement().moveToElement();
        this.destroyButton.click();
        this.getBaseElement().waitForDisappeared();
    }

    public String getText() {
       return this.labelWithText.getText();
    }

    public void clickOnLabel() {
        this.labelWithText.click();
    }

    public void waitForPoint() {
        this.getBaseElement().waitForElement();
    }

    public boolean isExist() {
        return  this.getBaseElement().isExist();
    }
}
