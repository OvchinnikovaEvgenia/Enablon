package tests;

import pages.TodosPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import testUtils.TestUtils;
import java.util.stream.IntStream;

public class NegativeTests extends BaseTest {

    @Test
    public void addSimilarPoints() {
        TodosPage page = getTodosPage();
        String text = TestUtils.getRandomString();
        page.enterTextOfThePoint(text);
        page.enterTextOfThePoint(text);
        Assert.assertTrue(page.isListAsExpected(), "Similar points are not present in todo list");
    }

    @Parameters({"textLength"})
    @Test
    public void addPointWithLargeText(String textLength) {
        TodosPage page = getTodosPage();
        String text = TestUtils.getRandomString(Integer.parseInt(textLength));
        page.enterTextOfThePoint(text);
        Assert.assertTrue(page.isListAsExpected(), "Large text did not add to the point");
    }

    @Parameters({"numberOfPoints"})
    @Test
    public void addTooManyPoints(String numberOfPoints) {
        TodosPage page = getTodosPage();
        IntStream.range(0, Integer.parseInt(numberOfPoints)).forEach(
                point -> {
                    String text = TestUtils.getRandomString();
                    page.enterTextOfThePoint(text);
                }
        );
        Assert.assertTrue(page.isListAsExpected(), "All points did not add to the list");
    }

    @Test
    public void shouldDeleteEmptyPoint() {
        TodosPage page = getTodosPage();
        String text = TestUtils.getRandomString();
        page.enterTextOfThePoint(text);
        String newText = TestUtils.getRandomString();
        page.enterTextOfThePoint(newText);
        page.clearEndExitFromEditing(newText);
        page.clickOnPoint(text);
        Assert.assertFalse(page.isListContainsEmptyPoint(), "List contains empty point");
    }
}
