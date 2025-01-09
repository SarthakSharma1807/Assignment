package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    WebDriver driver;

    By htmlTag = By.tagName("html");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    
//Method to Navigate to the Web site
    public void open() {
        driver.get("https://www.elpais.com/");
    }
    
//Method to get the value of the lang attribute, this contains what language is being used
    public String getLanguageAttribute() {
        WebElement htmlElement = driver.findElement(htmlTag);
        return htmlElement.getDomAttribute("lang");
    }
}
