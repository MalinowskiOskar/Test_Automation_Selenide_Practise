package tests;

import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import org.openqa.selenium.JavascriptExecutor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import static com.codeborne.selenide.WebDriverRunner.source;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
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
    public void BrokenImages() {
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[3]/a")).click();
        assertTrue("Broken image1", $(By.xpath("/html/body/div[2]/div/div/img[1]")).isImage()); // fail
        assertTrue("Broken image2", $(By.xpath("/html/body/div[2]/div/div/img[2]")).isImage()); // fail
        assertTrue("Broken image3", $(By.xpath("/html/body/div[2]/div/div/img[3]")).isImage()); // pass
    }

    @Test(groups = "Website", priority = 4)
    public void ChallengingDom() {
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[4]/a")).click();
        $(".button").click();
        $(".alert").click();
        $(".success").click();
        $$(By.xpath("/html/body/div[2]/div/div/div/div/div[2]/table/tbody/tr")).findBy(text("Definiebas4")).find(By.linkText("edit")).click();
        $$(By.xpath("/html/body/div[2]/div/div/div/div/div[2]/table/tbody/tr")).findBy(text("Consequuntur9")).find(By.linkText("delete")).click();
    }

    @Test(groups = "Website", priority = 5)
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

    @Test(groups = "Website", priority = 6)
    public void ContextMenu() throws IOException {
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[6]/a")).click();
        SelenideElement menu_area = $("#hot-spot");
        menu_area.contextClick();
        Runtime.getRuntime().exec("C:\\Users\\nebre\\Testy_Automatyczne\\AutoIT\\presskeysboard.exe");
        sleep(2000);
        switchTo().alert().accept();
    }

    @Test(groups = "Website", priority = 7)
    public void DisappearingElements() {
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[7]/a")).click();
        SelenideElement gallery = $(By.xpath("//*[@id=\"content\"]/div/ul/li[5]/a"));
        for (int i = 1; i < 10; i++) {
            gallery.shouldBe(visible);
            refresh();
        }
    }

    @Test(groups = "Website", priority = 8)
    public void DropdownList() {
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[9]/a")).click();
        SelenideElement droplist = $("#dropdown");
        droplist.selectOption("Option 1");
        droplist.shouldHave(text("Option 1"));
        droplist.selectOption("Option 2");
        droplist.shouldHave(text("Option 2"));
    }

    @Test(groups = "Website", priority = 9)
    public void Dynamic_Controls() {
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[11]/a")).click();
        sleep(1111);
        SelenideElement button = $("#btn");
        SelenideElement checkbox = $("#checkbox");
        SelenideElement message = $("#message");
        checkbox.shouldNotBe(checked);
        button.click();
        message.waitUntil(visible,5000).shouldHave(text("It's gone!"));
        checkbox.shouldNotBe(visible);
        button.click();
        checkbox.waitUntil(visible,5000);
        message.waitUntil(visible,5000).shouldHave(text("It's back!"));
    }

    @Test(groups = "Website", priority = 10)

    public void DynamicLoadingElementIsHidden() {
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[12]/a")).click();
        $(By.xpath("//*[@id=\"content\"]/div/a[1]")).click();
        $(By.xpath("//*[@id=\"finish\"]/h4")).shouldBe(hidden);
        $(By.xpath("//*[@id=\"start\"]/button")).click();
        $(By.id("loading")).shouldBe(visible);
        $(By.xpath("//*[@id=\"finish\"]/h4")).waitUntil(visible, 5000);
    }

    @Test(groups = "Website", priority = 11)
    public void DynamicLoadingElementRenderedAfterTheFact() {
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[12]/a")).click();
        $(By.xpath("//*[@id=\"content\"]/div/a[2]")).click();
        $(By.xpath("//*[@id=\"finish\"]/h4")).shouldNot(exist);
        $(By.xpath("//*[@id=\"start\"]/button")).click();
        $(By.xpath("//*[@id=\"finish\"]/h4")).waitUntil(visible, 5000);
    }

    @Test(groups = "Website", priority = 12)
    public void ExitIntent() throws IOException {
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[13]/a")).click();
        Runtime.getRuntime().exec("C:\\Users\\nebre\\Testy_Automatyczne\\AutoIT\\mousemove.exe");
        $(By.xpath("//*[@id=\"ouibounce-modal\"]/div[2]/div[2]/p")).shouldBe(visible);
        $(By.xpath("//*[@id=\"ouibounce-modal\"]/div[2]/div[2]/p")).shouldHave(text("It's commonly used to encourage a user to take an action (e.g., give their e-mail address to sign up for something)."));
        $(By.xpath("//*[@id=\"ouibounce-modal\"]/div[2]/div[3]/p")).shouldHave(text("Close")).click();
    }

    @Test(groups = "Website", priority = 13)
    public void FileDownload() throws FileNotFoundException {
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[14]/a")).click();
        SelenideElement file = $(By.xpath("//*[@id=\"content\"]/div/a[1]"));
        file.shouldHave(text("UploadFileTest.txt"));
        file.download();
    }

    @Test(groups = "Website", priority = 14)
    public void FileUpload(){
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[15]/a")).click();
        SelenideElement fileupload = $("#file-upload");
        SelenideElement filesubmit = $("#file-submit");
        SelenideElement uploadedfile = $("#uploaded-files");
        File file = $(fileupload).uploadFile(new File("C:\\Users\\nebre\\Testy_Automatyczne\\files\\UploadFileTest.txt"));
        Assert.assertTrue(file.exists());
        filesubmit.click();
        uploadedfile.waitUntil(visible,5000).shouldHave(text("UploadFileTest.txt "));
    }

    @Test(groups = "Website", priority = 15)
    public void FloatingMenu() {
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[16]/a")).click();
        SelenideElement menu = $("#menu");
        SelenideElement pagefooter = $(By.xpath("//*[@id=\"page-footer\"]/div/div/a"));
        menu.shouldBe(visible);
        pagefooter.scrollTo();
        menu.shouldBe(visible);
    }

    @Test(groups = "Website", priority = 16)
    public void ForgotPassword() {
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[17]/a")).click();
        SelenideElement email = $("#email");
        SelenideElement submitbutton = $("#form_submit");
        SelenideElement resetmail = $(withText("no-reply@the-internet.herokuapp.com"));
        SelenideElement content = $("#content");
        email.setValue("testowanie_automatyczne@mailinator.com");
        submitbutton.click();
        content.shouldHave(text("Your e-mail's been sent!"));
        open("https://www.mailinator.com/v2/inbox.jsp?zone=public&query=testowanie_automatyczne#/#inboxpane");
        resetmail.waitUntil(visible,5000);
    }

    @Test(groups = "Website", priority = 17)
    public void FormAuthentication() {
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[18]/a")).click();
        SelenideElement username = $("#username");
        SelenideElement password = $("#password");
        SelenideElement messege = $("#flash");
        SelenideElement button = $(By.xpath("//*[@id=\"login\"]/button"));
        SelenideElement logoutbutton = $(By.xpath("//*[@id=\"content\"]/div/a"));

        username.setValue("");
        password.setValue("");
        button.click();
        messege.waitUntil(visible,5000).shouldHave(text("Your username is invalid!"));

        username.setValue("tomsmith");
        password.setValue("");
        button.click();
        messege.waitUntil(visible,5000).shouldHave(text("Your password is invalid!"));

        username.setValue("tomsmith");
        password.setValue("SuperSecretPassword!");
        button.click();
        messege.waitUntil(visible,5000).shouldHave(text("You logged into a secure area!"));
        logoutbutton.click();
        messege.waitUntil(visible,5000).shouldHave(text("You logged out of the secure area!"));
    }

    @Test(groups = "Website", priority = 18)
    public void NestedFrames() {
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[19]/a")).click();
        SelenideElement link = $(By.xpath("//*[@id=\"content\"]/div/ul/li[1]/a"));
        link.click();

        switchTo().frame("frame-top");
        switchTo().frame("frame-left");
        assertThat(source(), containsString("LEFT"));

        switchTo().defaultContent();
        switchTo().frame("frame-top");
        switchTo().frame("frame-middle");
        assertThat(source(), containsString("MIDDLE"));

        switchTo().defaultContent();
        switchTo().frame("frame-top");
        switchTo().frame("frame-right");
        assertThat(source(), containsString("RIGHT"));

        switchTo().defaultContent();
        switchTo().frame("frame-bottom");
        assertThat(source(), containsString("BOTTOM"));
    }

    @Test(groups = "Website", priority = 19)
    public void iFrame() {
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[19]/a")).click();
        SelenideElement link = $(By.xpath("//*[@id=\"content\"]/div/ul/li[2]/a"));
        link.click();

        switchTo().frame("mce_0_ifr");
        SelenideElement editor = $("#tinymce");
        String before_text = editor.text();
        editor.clear();
        editor.setValue("Hello World!");
        String after_text = editor.text();
        Assert.assertNotEquals(after_text,before_text);
        System.out.println(before_text);
        System.out.println(after_text);
    }

    @Test(groups = "Website", priority = 20)
    public void HorizontalSlider() {
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[21]/a")).click();
        SelenideElement slider = $(By.xpath("//*[@id=\"content\"]/div/div/input"));
        SelenideElement range = $("#range");
        range.shouldHave(text("0"));
        slider.sendKeys(Keys.ARROW_RIGHT);
        range.shouldHave(text("0.5"));
        slider.sendKeys(Keys.ARROW_RIGHT);
        range.shouldHave(text("1"));
    }

    @Test(groups = "Website", priority = 21)
    public void Hovers() {
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[22]/a")).click();
        SelenideElement user1 = $("#content > div > div:nth-child(3) > img");
        SelenideElement user1text = $(By.xpath("//*[@id=\"content\"]/div/div[1]/div/h5"));
        SelenideElement user2 = $("#content > div > div:nth-child(4) > img");
        SelenideElement user2text = $(By.xpath("//*[@id=\"content\"]/div/div[2]/div/h5"));
        SelenideElement user3 = $("#content > div > div:nth-child(5) > img");
        SelenideElement user3text = $(By.xpath("//*[@id=\"content\"]/div/div[3]/div/h5"));
        user1.hover();
        user1text.waitUntil(appear,5000).shouldHave(text("name: user1"));
        user2.hover();
        user2text.waitUntil(appear,5000).shouldHave(text("name: user2"));
        user3.hover();
        user3text.waitUntil(appear,5000).shouldHave(text("name: user3"));
    }
    @Test(groups = "Website", priority = 22)
    public void InfiniteScroll() {
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[23]/a")).click();
        SelenideElement page_footer = $("#page-footer");
        SelenideElement element1page = $(By.xpath("//*[@id=\"content\"]/div/div/div/div/div[2]"));
        SelenideElement element2page = $(By.xpath("//*[@id=\"content\"]/div/div/div/div/div[4]"));
        sleep(1000);
        element1page.waitUntil(visible,5000);
        element2page.shouldNot(exist);
        int elementPosition = page_footer.getLocation().getY();
        String js = String.format("window.scrollTo(0, 5000)", elementPosition);
        JavascriptExecutor jsx = (JavascriptExecutor) getWebDriver();
        jsx.executeScript(js, "");
        element2page.waitUntil(visible,5000);
        element2page.shouldBe(visible);
        sleep(1000);
    }
}



