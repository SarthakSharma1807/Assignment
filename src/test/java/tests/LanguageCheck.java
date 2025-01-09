package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;

public class LanguageCheck extends BaseTest {

    @Test
    public void verifyLanguageIsSpanish() {
        HomePage homePage = new HomePage(getDriver());
      //Opens the web site
        homePage.open();
        String langAttribute = homePage.getLanguageAttribute();
        Assert.assertNotNull(langAttribute, "Language attribute is not set.");
      //Verifies that the language is Spanish or not by checking the value ES
        Assert.assertTrue(langAttribute.toLowerCase().contains("es"), "The website's text is not in Spanish.");
    }
}
