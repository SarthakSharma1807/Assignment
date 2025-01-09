package tests;

import org.testng.annotations.Test;
import pageObjects.OpinionPage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.List;
import org.openqa.selenium.WebElement;

public class ScrapingOpinionTest extends BaseTest {

    @Test
    public void scrapeArticles() throws IOException {
        OpinionPage opinionPage = new OpinionPage(getDriver());
        opinionPage.open();
        opinionPage.acceptCookies();

        List<WebElement> articles = opinionPage.getArticles();

        for (int i = 0; i < Math.min(articles.size(), 5); i++) {
            WebElement article = articles.get(i);
            String title = opinionPage.getTitle(article);
            String content = opinionPage.getContent(article);

            // Print the title and content of each article
            System.out.println("Title: " + title);
            System.out.println("Content: " + content);

            // Check for the cover image element and retrieve the image URL
            String imageUrl = opinionPage.getCoverImageUrl(article);
            if (imageUrl != null && !imageUrl.isEmpty()) {
                System.out.println("Cover Image URL: " + imageUrl); // Print the image URL for debugging

                try {
                    URL url = new URL(imageUrl);
                    File outputFile = new File("cover_image_" + (i + 1) + ".jpg");
                    try (InputStream in = url.openStream(); 
                    	FileOutputStream fos = new FileOutputStream(outputFile)) {
                        byte[] buffer = new byte[4096];
                        int bytesRead;
                        while ((bytesRead = in.read(buffer)) != -1) {
                            fos.write(buffer, 0, bytesRead);
                        }
                    }
                } catch (MalformedURLException e) {
                    System.err.println("Malformed URL: " + imageUrl);
                    e.printStackTrace();
                } catch (IOException e) {
                    System.err.println("Error downloading image: " + imageUrl);
                    e.printStackTrace();
                }
            } else {
                System.err.println("Cover image element not found.");
            }
        }
    }
}
