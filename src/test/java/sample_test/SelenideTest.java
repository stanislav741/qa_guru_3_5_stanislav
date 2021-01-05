package sample_test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.github.eroshenkoam.allure.NamedBy.css;
import static io.qameta.allure.Allure.step;

public class SelenideTest {

    private final static String REPOSITORY = "eroshenkoam/allure-example";
    private final static String USER = "eroshenkoam";
    private final static int ISSUE_NUMBER = 68;

    @Test
    public void testGitHub() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");

        $(css(".header-search-input").as("Search field")).click();
        $(css(".header-search-input").as("Search field")).sendKeys(REPOSITORY);
        $(css(".header-search-input").as("Search field")).submit();

        $(By.linkText(REPOSITORY)).click();
        $(withText("Issuesy")).click();
        $(withText("#" + ISSUE_NUMBER)).should(Condition.exist);
    }

}
