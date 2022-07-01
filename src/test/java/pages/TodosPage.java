package pages;

import framework.GeneralTestSettings;
import framework.elements.*;
import framework.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TodosPage extends BasePage {

    private static Input todoInput = new Input(By.xpath("//input[contains(@class, 'new-todo')]"), "ToDo input");
    private List<Point> listOfPoints = new ArrayList<>();
    private Label allPoints = new Label(By.xpath("//ul[contains(@class, 'todo-list')]//li//label"), "All points");

    public TodosPage(String name) {
        super(todoInput, name);
    }

    public void enterTextOfThePoint(String text) {
        GeneralTestSettings.getLogger().info("Enter text to the point");
        Point point = Point.getPointByText(text);
        todoInput.sendKeys(text);
        todoInput.sendKeys(Keys.ENTER);
        point.waitForPoint();
        if (point.isDisplayed())
            listOfPoints.add(point);
    }

    public void completePoint(String text) {
        GeneralTestSettings.getLogger().info("Complete point");
        getPointByText(text).getCompletedInput().click();
    }

    public void destroyPointFromList(String text) {
        Point point = getPointByText(text);
        GeneralTestSettings.getLogger().info("Destroy point");
        point.clickDestroyButton();
        listOfPoints.remove(point);
    }

    public void clickOnPoint(String text) {
        getPointByText(text).clickOnLabel();
    }

    public void editPoint(String oldText, String newText) {
        Point oldPoint = getPointByText(oldText);
        Point newPoint = oldPoint.clearPoint().insertTextToPoint(newText);
        listOfPoints.remove(oldPoint);
        listOfPoints.add(newPoint);
    }

    public void clearEndExitFromEditing(String text) {
        Point oldPoint = getPointByText(text);
        Point newPoint = oldPoint.clearPoint();
        listOfPoints.remove(oldPoint);
        listOfPoints.add(newPoint);
    }

    public boolean isListAsExpected() {
        GeneralTestSettings.getLogger().info("Compare lists of points");
        List<String> currentPointsText = allPoints.getTextFromAll();
        List<String> expectedPointsText = listOfPoints
                .stream()
                .map(element -> element.getText())
                .sorted()
                .collect(Collectors.toList());
        return currentPointsText.equals(expectedPointsText);
    }

    public boolean isListContainsEmptyPoint() {
        GeneralTestSettings.getLogger().info("Check on empty point at the list");
        return allPoints.getTextFromAll().stream().anyMatch(element -> element.equals(""));
    }

    private boolean isPointUnique(String text) {
       return allPoints.getTextFromAll().stream().anyMatch(element -> element.equals(text));
    }

    public boolean isPointDisplayed(String text) {
        return getPointByText(text).isDisplayed();
    }

    public boolean isPointCompleted(String text) {
        return getPointByText(text).isCompleted();
    }

    public boolean isPointExist(String text) {
        if (getPointByText(text) != null)
            return getPointByText(text).isExist();
        return false;
    }

    private Point getPointByText(String text) {
        if (isPointUnique(text)) {
            return listOfPoints.stream().filter(
                    point -> point.getText().equals(text)
            ).collect(Collectors.toList()).get(0);
        }
        return null;
    }
}
