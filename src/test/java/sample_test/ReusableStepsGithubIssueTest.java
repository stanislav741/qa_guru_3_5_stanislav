package sample_test;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class ReusableStepsGithubIssueTest {

    private final static String REPOSITORY = "eroshenkoam/allure-example";
    private final static String USER = "eroshenkoam";
    private final static int ISSUE_NUMBER = 68;

    @Test
    public void searchForIssue() {
        final BaseSteps steps = new BaseSteps();

        steps.openMainPage();
        steps.findRepository(REPOSITORY);
        steps.openRepository(REPOSITORY);
        steps.openIssuesList();
        steps.verifyIssue(ISSUE_NUMBER);
    }

    public static class BaseSteps {

        @Step("Open the main page")
        public void openMainPage() {
            open("https://github.com");
        }

        @Step("Find repository {name}")
        public void findRepository(final String name) {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(name);
            $(".header-search-input").submit();
        }

        @Step("Open repository {name}")
        public void openRepository(final String name) {
            $(By.linkText(name)).click();
        }

        @Step("Open Issues list")
        public void openIssuesList() {
            $(withText("Issues")).click();
        }

        @Step("Verifying Issue {number}")
        public void verifyIssue(final int number) {
            $(withText("#" + ISSUE_NUMBER)).should(Condition.exist);
        }
    }

}
