package tests;

import pages.TodosPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import testUtils.TestUtils;

public class PositiveTests extends BaseTest {

    @Test
    public void addItem() {
        TodosPage page = getTodosPage();
        String text = TestUtils.getRandomString();
        page.enterTextOfThePoint(text);
        Assert.assertTrue(page.isPointDisplayed(text), "Point did not add to the list");
        Assert.assertTrue(page.isListAsExpected(), "Point did not add to the list");
    }

    @Test
    public void completePoint() {
        TodosPage page = getTodosPage();
        String text = TestUtils.getRandomString();
        page.enterTextOfThePoint(text);
        page.completePoint(text);
        Assert.assertTrue(page.isPointCompleted(text), "Point did not display as completed");
        Assert.assertTrue(page.isListAsExpected(), "Point did not add to the list");
    }

    @Test
    public void destroyPoint() {
        TodosPage page = getTodosPage();
        String text = TestUtils.getRandomString();
        page.enterTextOfThePoint(text);
        page.destroyPointFromList(text);
        Assert.assertFalse(page.isPointExist(text), "Point did not destroy");
        Assert.assertTrue(page.isListAsExpected(), "Point did not destroy");
    }

    @Test
    public void editPoint() {
        TodosPage page = getTodosPage();
        String text = TestUtils.getRandomString();
        page.enterTextOfThePoint(text);
        String newText = TestUtils.getRandomString();
        page.editPoint(text, newText);
        Assert.assertTrue(page.isListAsExpected(), "Point did not change after editing");
    }
}
