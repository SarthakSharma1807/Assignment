package tests;

import org.testng.annotations.Test;
import pageObjects.OpinionPage;
import utils.TranslationUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TranslateAndAnalyze extends BaseTest {

    @Test
    public void scrapeAndAnalyzeArticles() throws IOException {
        OpinionPage opinionPage = new OpinionPage(getDriver());
        opinionPage.open();
        opinionPage.acceptCookies();

        List<String> titles = opinionPage.getFirstFiveArticleTitles();

        // Translate each title to English using RapidAPI
        List<String> translatedTitles = new ArrayList<>();
        for (String title : titles) {
            translatedTitles.add(TranslationUtil.translateText(title, "es", "en"));
        }

        // Print the translated titles
        System.out.println("Translated Titles:");
        for (String translatedTitle : translatedTitles) {
            System.out.println(translatedTitle);
        }

        // Analyze the translated titles to find repeated words
        Map<String, Integer> wordCounts = new HashMap<>();
        for (String translatedTitle : translatedTitles) {
            String[] words = translatedTitle.split("\\s+");
            for (String word : words) {
                word = word.toLowerCase().replaceAll("[^a-z]", ""); // Convert to lowercase and remove non-alphabetic characters
                wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
            }
        }

        // Print words that are repeated more than twice
        System.out.println("Repeated Words:");
        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            if (entry.getValue() > 2) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }
}
