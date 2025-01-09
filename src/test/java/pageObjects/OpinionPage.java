package pageObjects;
import utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class OpinionPage {
    WebDriver driver;

    By acceptCookiesButton = By.xpath("//button[@id='didomi-notice-agree-button']");
    By articleTitles = By.xpath("//article[@class='c c-o c-d c--c c--m   ']//h2[@class='c_t c_t-i ' ]/a");
    By articleContents = By.xpath(".//p[@class='c_d']");
    By coverImage = By.xpath(".//figure[@class='c_m a_m-h c_m--nf']//img");

    public OpinionPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://elpais.com/opinion/editoriales/");
    }

    public void acceptCookies() {
        WaitUtils waitUtils = new WaitUtils(driver);
        try {
            WebElement acceptButton = waitUtils.waitForElementToBeClickable(acceptCookiesButton, 10);
            acceptButton.click();
        } catch (Exception e) {
            System.out.println("Accept Cookies button not found or not clickable: " + e.getMessage());
        }
    }

    
    public List<WebElement> getArticles() {
        return driver.findElements(By.xpath("//article[@class='c c-o c-d c--c c--m   ']"));
    }

    public List<String> getFirstFiveArticleTitles() {
        List<String> titles = new ArrayList<>();
        List<WebElement> articles = driver.findElements(articleTitles);
        for (int i = 0; i < Math.min(articles.size(), 5); i++) {
            titles.add(articles.get(i).getText());
        }
        return titles;
    }

    public String getTitle(WebElement article) {
        return article.findElement(By.xpath(".//h2[@class='c_t c_t-i ' ]/a")).getText();
    }

    public String getContent(WebElement article) {
        return article.findElement(By.xpath(".//p[@class='c_d']")).getText();
    }

    public String getCoverImageUrl(WebElement article) {
        WebElement imgElement = article.findElement(By.xpath(".//figure[@class='c_m a_m-h c_m--nf']//img"));
        return (imgElement != null) ? imgElement.getAttribute("src") : null;
    }
}
