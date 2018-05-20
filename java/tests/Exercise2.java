package tests;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import org.openqa.selenium.Cookie;
import java.util.Set;

import static java.lang.Thread.sleep;
import static org.junit.Assume.assumeFalse;

public class Exercise2 extends Setup {

    @Test(groups = "Website", priority = 1)
    public void ABTest() {
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[1]/a")).click();
        $(".example > h3:nth-child(1)").shouldHave(text("A/B Test"));
        open("http://the-internet.herokuapp.com/");
        getWebDriver().manage().addCookie(new Cookie("optimizelyOptOut", "true"));
        Set<Cookie> cookieSet = getWebDriver().manage().getCookies();
        assumeFalse((cookieSet.isEmpty()));
        $(By.xpath("//*[@id=\"content\"]/ul/li[1]/a")).click();
        $(".example > h3:nth-child(1)").shouldHave(text("No A/B Test"));
        getWebDriver().manage().deleteAllCookies();
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[1]/a")).click();
        //refresh();
        $(".example > h3:nth-child(1)").shouldHave(text("A/B Test"));
    }

    @Test(groups = "Website", priority = 2)
    public void BasicAuth() {
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[2]/a")).click();
        $("body").shouldHave(text("Not authorized"));
        open("http://the-internet.herokuapp.com/");
        open("http://admin:admin@the-internet.herokuapp.com/basic_auth");
        $(".example > p:nth-child(2)").shouldHave(text("Congratulations! You must have the proper credentials."));
    }

    @Test(groups = "Website", priority = 3)
    public void ChallengingDom() {
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[4]/a")).click();
        $(".button").click();
        $(".alert").click();
        $(".success").click();
        $$(By.xpath("/html/body/div[2]/div/div/div/div/div[2]/table/tbody/tr")).findBy(text("Definiebas4")).find(By.linkText("edit")).click();
        $$(By.xpath("/html/body/div[2]/div/div/div/div/div[2]/table/tbody/tr")).findBy(text("Consequuntur9")).find(By.linkText("delete")).click();
    }

    @Test(groups = "Website", priority = 4)
    public void CheckBoxes() {
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[5]/a")).click();
        $(By.xpath("//*[@id=\"checkboxes\"]/input[1]")).shouldNotBe(checked);
        $(By.xpath("//*[@id=\"checkboxes\"]/input[1]")).click();
        $(By.xpath("//*[@id=\"checkboxes\"]/input[1]")).shouldBe(checked);
        $(By.xpath("//*[@id=\"checkboxes\"]/input[2]")).shouldBe(checked);
        $(By.xpath("//*[@id=\"checkboxes\"]/input[2]")).click();
        $(By.xpath("//*[@id=\"checkboxes\"]/input[2]")).shouldNotBe(checked);
    }

    @Test(groups = "Website", priority = 5)
    public void DisappearingElements() {
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[7]/a")).click();
        SelenideElement gallery = $(By.xpath("//*[@id=\"content\"]/div/ul/li[5]/a"));
        for (int i = 1; i < 10; i++) {
            gallery.shouldBe(visible);
            refresh();
        }
    }

    @Test(groups = "Website", priority = 6)
    public void DynamicLoadingElementIsHidden() {
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[12]/a")).click();
        $(By.xpath("//*[@id=\"content\"]/div/a[1]")).click();
        $(By.xpath("//*[@id=\"finish\"]/h4")).shouldBe(hidden);
        $(By.xpath("//*[@id=\"start\"]/button")).click();
        $(By.id("loading")).shouldBe(visible);
        try {
            sleep(5500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        $(By.xpath("//*[@id=\"finish\"]/h4")).shouldBe(visible);
    }
    @Test(groups = "Website", priority = 7)
    public void DynamicLoadingElementRenderedAfterTheFact() {
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[12]/a")).click();
        $(By.xpath("//*[@id=\"content\"]/div/a[2]")).click();
        $(By.xpath("//*[@id=\"finish\"]/h4")).shouldNot(exist);
        $(By.xpath("//*[@id=\"start\"]/button")).click();
        try {
            sleep(5500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        $(By.xpath("//*[@id=\"finish\"]/h4")).shouldBe(visible);
    }
    @Test(groups = "Website", priority = 8)
    public void ExitIntent() {
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[13]/a")).click();

    }
}



