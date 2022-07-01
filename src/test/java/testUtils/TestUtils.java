package testUtils;

import java.util.Random;

public class TestUtils {

    private static Random random = new Random();

    public static String getRandomString(int ...userLength) {
        int length = userLength.length == 0 ? random.nextInt(20) + 1 : userLength[0];
        String randomString = "";
        while (randomString.length() < length) {
            randomString += (char)(65 + random.nextInt(25));
        }
        return randomString;
    }
}
