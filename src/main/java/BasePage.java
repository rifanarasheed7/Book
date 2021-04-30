import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class BasePage {
    public WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement locateElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    //todo findelements ,operation sendkeys etc
    public void click(By locator) {
        locateElement(locator).click();
    }

    public String takeScreenshot(String screenshotName) throws IOException {

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        Random random = new Random();
        String fileName = "Screenshot" + random.nextInt(10000);

        Files.move
                (Paths.get(screenshot.getAbsolutePath()),
                        Paths.get(System.getProperty("user.dir") + "/report/" + fileName + ".png"));
        return fileName + ".png";
    }

    public String getText(By locator) {
        return locateElement(locator).getText();
    }

    public void citySelected(String cityName) {
        locateElement(By.xpath("//body/div[@id='modal-root']/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")).sendKeys(cityName + Keys.ENTER);
    }

    public void waits(By locator) {
        int i = 0;
        while (i < 10) {
            try {
                WebDriverWait wait1 = new WebDriverWait(driver, 20);
                wait1.until(ExpectedConditions.elementToBeClickable(locator));
                break;
            } catch (Exception e) {
                i++;
            }
        }
    }
}
