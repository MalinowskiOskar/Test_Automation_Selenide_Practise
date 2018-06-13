package tests;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.DemoQAPage;
import pages.SignUpPage;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.refresh;
import static org.junit.Assert.assertEquals;

public class Exercise4 extends Setup {


    /**
     * Looking for solution add this to SignUpPage
     */
    private SelectsPage pageWithSelects;
    private static class SelectsPage{
        @FindBy(className = "legend")
        List<SelenideElement> error;
    }

    @Test(groups = "Website", priority = 1)
    public void gotoSignUpPage() {
        DemoQAPage demoQAPage = new DemoQAPage();
        demoQAPage.open();
        demoQAPage.goToSignUpPage();
    }
    @Test(groups = "Website", priority = 2)
    public void makingAccountWithoutFill(){
        SignUpPage signUpPage = new SignUpPage();
        signUpPage.waitforvisible.waitUntil(visible,5000);
        signUpPage.submit();
        pageWithSelects = page(SelectsPage.class);
        assertEquals(7, pageWithSelects.error.size());
        pageWithSelects.error.get(0).shouldHave(text("* This field is required"));
        pageWithSelects.error.get(1).shouldHave(text("* This field is required"));
        pageWithSelects.error.get(2).shouldHave(text("* This field is required"));
        pageWithSelects.error.get(3).shouldHave(text("* This field is required"));
        pageWithSelects.error.get(4).shouldHave(text("* This field is required"));
        pageWithSelects.error.get(5).shouldHave(text("* This field is required"));
        pageWithSelects.error.get(6).shouldHave(text("* This field is required"));
    }
    @Test(groups = "Website", priority = 3)
    @Parameters({"CorrectName","CorrectSurname","InvalidPhone","CorrectUser","CorrectMail","CorrectPass","CorrectCpass"})
    public void makingAccountWithInvalidPhone(String name,String surname,String tel, String user,String mail,String pass,String cPass){
        SignUpPage signUpPage = new SignUpPage();
        signUpPage.waitforvisible.waitUntil(visible,5000);
        signUpPage.hobby.click();
        signUpPage.status.click();
        signUpPage.login(name,surname,tel,user,mail,pass,cPass);
        signUpPage.submit();
        pageWithSelects.error.get(0).shouldHave(text("* Minimum 10 Digits starting with Country Code"));
    }
    @Test(groups = "Website", priority = 4)
    @Parameters({"CorrectName","CorrectSurname","CorrectPhone","CorrectUser","InvalidMail","CorrectPass","CorrectCpass"})
    public void makingAccountWithInvalidMail(String name,String surname,String tel, String user,String mail,String pass,String cPass){
        SignUpPage signUpPage = new SignUpPage();
        signUpPage.waitforvisible.waitUntil(visible,5000);
        signUpPage.hobby.shouldBe(checked);
        signUpPage.status.shouldBe(checked);
        signUpPage.login(name,surname,tel,user,mail,pass,cPass);
        signUpPage.submit();
        pageWithSelects.error.get(0).shouldHave(text("* Invalid email address"));
    }

    @Test(groups = "Website", priority = 5)
    @Parameters({"CorrectName","CorrectSurname","CorrectPhone","CorrectUser","CorrectMail","ShortPass","ShortPass"})
    public void makingAccountWithShortPassword(String name,String surname,String tel, String user,String mail,String pass,String cPass){
        SignUpPage signUpPage = new SignUpPage();
        signUpPage.waitforvisible.waitUntil(visible,5000);
        signUpPage.hobby.shouldBe(checked);
        signUpPage.status.shouldBe(checked);
        signUpPage.login(name,surname,tel,user,mail,pass,cPass);
        signUpPage.submit();
        pageWithSelects.error.get(0).shouldHave(text("* Minimum 8 characters required"));
    }
    @Test(groups = "Website", priority = 6)
    @Parameters({"CorrectName","CorrectSurname","CorrectPhone","CorrectUser","CorrectMail","CorrectPass","InvalidCpass"})
    public void makingAccountWithInvalidConfirmPassword(String name,String surname,String tel, String user,String mail,String pass,String cPass){
        SignUpPage signUpPage = new SignUpPage();
        signUpPage.waitforvisible.waitUntil(visible,5000);
        signUpPage.hobby.shouldBe(checked);
        signUpPage.status.shouldBe(checked);
        signUpPage.login(name,surname,tel,user,mail,pass,cPass);
        signUpPage.submit();
        pageWithSelects.error.get(0).shouldHave(text("* Fields do not match"));
    }

    /**
     * SignUp not work without refresh();
     */

    @Test(groups = "Website", priority = 7)
    @Parameters({"CorrectName","CorrectSurname","CorrectPhone","UsedUser","CorrectMail","CorrectPass","CorrectCpass"})
    public void makingAccountWithUsedUsername(String name,String surname,String tel, String user,String mail,String pass,String cPass){
        SignUpPage signUpPage = new SignUpPage();
        refresh();
        signUpPage.waitforvisible.waitUntil(visible,5000);
        signUpPage.hobby.click();
        signUpPage.status.click();
        signUpPage.login(name,surname,tel,user,mail,pass,cPass);
        signUpPage.submit();
        signUpPage.messege.waitUntil(visible,5000).shouldHave(text("Error: Username already exists"));
    }

    @Test(groups = "Website", priority = 8)
    @Parameters({"CorrectName","CorrectSurname","CorrectPhone","CorrectUser","UsedMail","CorrectPass","CorrectCpass"})
    public void makingAccountWithUsedMail(String name,String surname,String tel, String user,String mail,String pass,String cPass){
        SignUpPage signUpPage = new SignUpPage();
        refresh();
        signUpPage.waitforvisible.waitUntil(visible,5000);
        signUpPage.hobby.click();
        signUpPage.status.click();
        signUpPage.login(name,surname,tel,user,mail,pass,cPass);
        signUpPage.submit();
        signUpPage.messege.waitUntil(visible,5000).shouldHave(text("Error: E-mail address already exists"));
    }

    @Test(groups = "Website", priority = 9)
    @Parameters({"CorrectName","CorrectSurname","CorrectPhone","CorrectUser","CorrectMail","CorrectPass","CorrectCpass"})
    public void makingCorrectAccount(String name,String surname,String tel, String user,String mail,String pass,String cPass){
        SignUpPage signUpPage = new SignUpPage();
        refresh();
        signUpPage.waitforvisible.waitUntil(visible,5000);
        signUpPage.hobby.click();
        signUpPage.status.click();
        signUpPage.login(name,surname,tel,user,mail,pass,cPass);
        signUpPage.submit();
        signUpPage.messege.waitUntil(visible,5000).shouldHave(text("Thank you for your registration"));
    }


}