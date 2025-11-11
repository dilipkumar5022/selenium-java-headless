package main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(options);
        try {
            System.out.println("Opening example.com ...");
            driver.get("https://example.com");

            String h1 = driver.findElement(By.tagName("h1")).getText();
            String p = driver.findElement(By.tagName("p")).getText();
            System.out.println("H1: " + h1);
            System.out.println("Paragraph (first 80 chars): " + (p.length()>80 ? p.substring(0,80) : p));

            java.io.File src = ((org.openqa.selenium.TakesScreenshot) driver)
                    .getScreenshotAs(org.openqa.selenium.OutputType.FILE);
            java.nio.file.Files.copy(src.toPath(), java.nio.file.Path.of("example_com_screenshot.png"),
                                     java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Screenshot saved: example_com_screenshot.png");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            driver.quit();
        }
    }
}