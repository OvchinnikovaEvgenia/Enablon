package framework.browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Browser {

    public static List<WebElement> findElements(By locator) {
        return BrowserFactory.getDriver().findElements(locator);
    }
}
