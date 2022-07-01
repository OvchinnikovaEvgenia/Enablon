package testUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class SettingsManager {

    private static String pathToSettingsFile = "src/main/resources/testSettings.properties";
    private static Properties properties;

    static {
        try (FileInputStream fileInputStream = new FileInputStream(pathToSettingsFile)) {
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getBaseURL() {
        return properties.getProperty("toDoListURL");
    }

    public static String getBrowser() {
        return properties.getProperty("browser");
    }

    public static Duration getWaitTime() {
        return Duration.ofSeconds(Long.parseLong(properties.getProperty("wait")));
    }
}
