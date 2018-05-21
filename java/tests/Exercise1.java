package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class Exercise1 extends Setup {

    @Test(groups = "Website", priority = 1)
    public void DiffrentWaysToFindsElements(){
        open("http://automationpractice.com/index.php");
        // search and click sign in button
        $(By.className("login"));                       //find element button Sign in by class
        $(byTitle("Log in to your customer account"));  //find same element by title
        $(withText("Sign in")).click();      //find same element withText and click

        // search and click create an account button
        $(byText("Create an account"));      //find element button Create an account by Text
        $(By.className("btn-default"));                 //find same element by class
//        $(".btn-default");                            //short version find element by class
        $(By.id("SubmitCreate")).click();               //find same element by id
//        $("#SubmitCreate");                           //short version find element by id

        // search and click button Women
        $(byTitle("Women"));
        $(".sf-with-ul");
        $(byText("Women")).click();
        $(byTitle("Printed Chiffon Dress")).scrollTo().click();
        // Exercise 1 Complete :)
    }

    @Test(groups = "Website", priority = 2)
    public void HoverAndCheckThatTextInImageIsCorrect() {
        open("http://automationpractice.com/index.php");
        $(byTitle("Women")).hover();                                            //only work correctly when you put cursor out of website
        $(byTitle("Evening Dresses")).should(appear).click();
        $(".category-name").shouldHave(exactText("Evening Dresses")).shouldNotHave(text("Casual Dresses"));
    }

    @Test(groups = "Website", priority = 3)
    public void ElementsCollectionExercise(){
        open("http://automationpractice.com/index.php");
        $(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/a")).click();
        $(".ajax_block_product").scrollTo();
        $$(".ajax_block_product").filterBy(text("Printed Summer Dress")).get(0).click();
//        ElementsCollection collection = $$(".color_pick");
//        collection.shouldHaveSize(4);
        $$(".color_pick").shouldHaveSize(4);
    }

    @Test(groups = "Website", priority = 4)
    public void CorrectSignIn(){
        open("http://automationpractice.com/index.php");
        $(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).click();
        $(By.id("email_create")).setValue("testowymail@testowymail.com");
        $(By.id("SubmitCreate")).click();
        $(By.id("id_gender1")).click();
        $(By.id("id_gender1")).shouldBe(checked);
        $(By.id("customer_firstname")).setValue("Tester");
        $(By.id("customer_lastname")).setValue("Testowski");
        $(By.id("passwd")).setValue("test123");
        $(By.id("days")).selectOptionByValue("8");
        $(By.id("months")).selectOption(11);
        $(By.id("years")).selectOptionByValue("1988");
        $(By.id("newsletter")).scrollTo();
        $(By.id("uniform-newsletter")).shouldNotBe(checked).click();
        $(By.id("uniform-optin")).click();
        $(By.id("optin")).shouldBe(checked);
        $(By.id("company")).setValue("Tester");
        $(By.id("address1")).setValue("Testowa ulica 1/1");
        $(By.id("city")).setValue("Test");
        $(By.xpath("//*[@id=\"id_state\"]")).selectOption("California");
        $(By.id("postcode")).setValue("50000");
        $(By.id("id_country")).selectOption("United States");
        $(By.id("other")).setValue("TESTAUTOMATYCZNY");
        $(By.id("phone_mobile")).setValue("999888777");
        $(By.id("alias")).setValue("TestAutomatyczny");
        $(By.id("submitAccount")).click();
    }
}
