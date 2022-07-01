package framework;

import framework.browser.BrowserFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import testUtils.SettingsManager;

import java.util.logging.Logger;

public class GeneralTestSettings {

    private static WebDriverWait wait;

    public static void setGeneralSettings() {
        wait = new WebDriverWait(BrowserFactory.getDriver(), SettingsManager.getWaitTime());
    }

    public static WebDriverWait getWait() {
        return wait;
    }

    public static Logger getLogger() {
        return Logger.getLogger("logger");
    }
}
