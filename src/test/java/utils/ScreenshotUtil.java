package utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    public static String capture(WebDriver driver, String name) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path dest = Path.of("target", "screenshots", sanitize(name) + ".png");
            Files.createDirectories(dest.getParent());
            Files.copy(src.toPath(), dest);
            return dest.toString();
        } catch (Exception e) {
            return null;
        }
    }

    private static String sanitize(String s) {
//        return s.replaceAll("[^a-zA-Z0-9\-_]", "_");
        return s.replaceAll("[^a-zA-Z0-9_-]", "_");
    }
}
