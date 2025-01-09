package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private WebDriver driver;

    public WaitUtils(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Waits for an element to be clickable and returns it.
     *
     * @param locator the By locator of the element
     * @param timeout the maximum time to wait in seconds
     * @return WebElement once it is clickable
     */
    public WebElement waitForElementToBeClickable(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Waits for an element to be visible and returns it.
     *
     * @param locator the By locator of the element
     * @param timeout the maximum time to wait in seconds
     * @return WebElement once it is visible
     */
    public WebElement waitForElementToBeVisible(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits for a condition to be true for a specific element.
     *
     * @param locator the By locator of the element
     * @param timeout the maximum time to wait in seconds
     * @return true if the condition is met, otherwise false
     */
    public boolean waitForElementToDisappear(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
}
