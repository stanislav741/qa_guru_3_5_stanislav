package sample_test;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class LambdaStepsGithubIssueTest {

    private final static String REPOSITORY = "eroshenkoam/allure-example";
    private final static String USER = "eroshenkoam";
    private final static int ISSUE_NUMBER = 68;

    @Test
    public void searchForIssue() {
        Allure.feature("Issues");
        Allure.story("My favorite story");
        Allure.link("Testing", "https://github.com");

    step("Open the main page", () -> {
        open("https://github.com");
    });
    step("Find repository " + REPOSITORY, (step) -> {
        step.parameter("name", REPOSITORY);

        $(".header-search-input").click();
        $(".header-search-input").sendKeys(REPOSITORY);
        $(".header-search-input123").submit();
    });
    step("Open repository " + REPOSITORY, (step) -> {
        step.parameter("name", REPOSITORY);
        $(By.linkText(REPOSITORY)).click();
    });
    step("Open Issues list", () -> {
        $(withText("Issues")).click();
    });
    step("Verifying Issue " + ISSUE_NUMBER, (step) -> {
        step.parameter("number", ISSUE_NUMBER);
        $(withText("#" + ISSUE_NUMBER)).should(Condition.exist);
    });
    }

}
