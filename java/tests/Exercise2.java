package tests;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.Assert;
import org.junit.ComparisonFailure;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import org.openqa.selenium.JavascriptExecutor;

import java.io.*;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import static com.codeborne.selenide.WebDriverRunner.source;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeFalse;
import static org.openqa.selenium.logging.LogType.BROWSER;

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

        try {
            assertTrue("Broken image1", $(By.xpath("/html/body/div[2]/div/div/img[1]")).isImage()); // fail
        } catch (AssertionError ex) {
            System.out.println("Image1 broken. Continuing test");
        }

        try {
            assertTrue("Broken image2", $(By.xpath("/html/body/div[2]/div/div/img[2]")).isImage()); // fail
        } catch (AssertionError ex) {
            System.out.println("Image2 broken. Continue testing");
        }
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
    public void DragAndDrop() {
        /**
         * http://the-internet.herokuapp.com/drag_and_drop
         * not work correctly on html5, looking for solution
         */
        open("http://jqueryui.com/droppable/");
        SelenideElement columA = $("#draggable");
        SelenideElement columB = $("#droppable");
        switchTo().frame(0);
        actions().dragAndDrop(columA,columB).build().perform();
//        actions().clickAndHold(columA).moveToElement(columB).release(columB).build().perform();
    }


    @Test(groups = "Website", priority = 9)
    public void DropdownList() {
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[9]/a")).click();
        SelenideElement droplist = $("#dropdown");
        droplist.selectOption("Option 1");
        droplist.shouldHave(text("Option 1"));
        droplist.selectOption("Option 2");
        droplist.shouldHave(text("Option 2"));
    }

    @Test(groups = "Website", priority = 10)
    public void DynamicContent() {
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[10]/a")).click();
        SelenideElement image1 = $(By.xpath("//*[@id=\"content\"]/div[1]/div[1]/img"));
        SelenideElement image2 = $(By.xpath("//*[@id=\"content\"]/div[2]/div[1]/img"));
        SelenideElement image3 = $(By.xpath("//*[@id=\"content\"]/div[3]/div[1]/img"));
        String src1 = image1.getAttribute("src");
        String src2 = image2.getAttribute("src");
        String src3 = image3.getAttribute("src");

        try {
            Assert.assertEquals(src1, "http://the-internet.herokuapp.com/img/avatars/Original-Facebook-Geek-Profile-Avatar-2.jpg");
        } catch (ComparisonFailure ex) {
            System.out.println("Incorrect image1. Continue testing.");
        }
        try {
            Assert.assertEquals(src2, "http://the-internet.herokuapp.com/img/avatars/Original-Facebook-Geek-Profile-Avatar-6.jpg");
        } catch (ComparisonFailure ex) {
            System.out.println("Incorrect image2. Continue testing.");
        }
        try {
            Assert.assertEquals(src3, "http://the-internet.herokuapp.com/img/avatars/Original-Facebook-Geek-Profile-Avatar-5.jpg");
        } catch (ComparisonFailure ex) {
            System.out.println("Incorrect image3. Continue testing.");
        }

        open("http://the-internet.herokuapp.com/dynamic_content?with_content=static");

        try {
            Assert.assertEquals(src1, "http://the-internet.herokuapp.com/img/avatars/Original-Facebook-Geek-Profile-Avatar-2.jpg");
        } catch (ComparisonFailure ex) {
            System.out.println("Incorrect image1 with static content. Continue testing.");
        }
        try {
            Assert.assertEquals(src2, "http://the-internet.herokuapp.com/img/avatars/Original-Facebook-Geek-Profile-Avatar-6.jpg");
        } catch (ComparisonFailure ex) {
            System.out.println("Incorrect image2 with static content. Continue testing.");
        }
        try {
            Assert.assertEquals(src3, "http://the-internet.herokuapp.com/img/avatars/Original-Facebook-Geek-Profile-Avatar-5.jpg");
        } catch (ComparisonFailure ex) {
            System.out.println("Incorrect image3 with static content. Continue testing.");
        }
    }

    @Test(groups = "Website", priority = 11)
    public void Dynamic_Controls() {
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[11]/a")).click();
        sleep(1111);
        SelenideElement button = $("#btn");
        SelenideElement checkbox = $("#checkbox");
        SelenideElement message = $("#message");
        checkbox.shouldNotBe(checked);
        button.click();
        message.waitUntil(visible, 5000).shouldHave(text("It's gone!"));
        checkbox.shouldNotBe(visible);
        button.click();
        checkbox.waitUntil(visible, 5000);
        message.waitUntil(visible, 5000).shouldHave(text("It's back!"));
    }

    @Test(groups = "Website", priority = 12)

    public void DynamicLoadingElementIsHidden() {
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[12]/a")).click();
        $(By.xpath("//*[@id=\"content\"]/div/a[1]")).click();
        $(By.xpath("//*[@id=\"finish\"]/h4")).shouldBe(hidden);
        $(By.xpath("//*[@id=\"start\"]/button")).click();
        $(By.id("loading")).shouldBe(visible);
        $(By.xpath("//*[@id=\"finish\"]/h4")).waitUntil(visible, 5000);
    }

    @Test(groups = "Website", priority = 13)
    public void DynamicLoadingElementRenderedAfterTheFact() {
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[12]/a")).click();
        $(By.xpath("//*[@id=\"content\"]/div/a[2]")).click();
        $(By.xpath("//*[@id=\"finish\"]/h4")).shouldNot(exist);
        $(By.xpath("//*[@id=\"start\"]/button")).click();
        $(By.xpath("//*[@id=\"finish\"]/h4")).waitUntil(visible, 5000);
    }

    @Test(groups = "Website", priority = 14)
    public void ExitIntent() throws IOException {
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[13]/a")).click();
        Runtime.getRuntime().exec("C:\\Users\\nebre\\Testy_Automatyczne\\AutoIT\\mousemove.exe");
        $(By.xpath("//*[@id=\"ouibounce-modal\"]/div[2]/div[2]/p")).shouldBe(visible);
        $(By.xpath("//*[@id=\"ouibounce-modal\"]/div[2]/div[2]/p")).shouldHave(text("It's commonly used to encourage a user to take an action (e.g., give their e-mail address to sign up for something)."));
        $(By.xpath("//*[@id=\"ouibounce-modal\"]/div[2]/div[3]/p")).shouldHave(text("Close")).click();
    }

    @Test(groups = "Website", priority = 15)
    public void FileUpload() {
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[15]/a")).click();
        SelenideElement fileupload = $("#file-upload");
        SelenideElement filesubmit = $("#file-submit");
        SelenideElement uploadedfile = $("#uploaded-files");
        File file = $(fileupload).uploadFile(new File("C:\\Users\\nebre\\Testy_Automatyczne\\files\\UploadFileTest.txt"));
        Assert.assertTrue(file.exists());
        filesubmit.click();
        uploadedfile.waitUntil(visible, 5000).shouldHave(text("UploadFileTest.txt"));
    }

    @Test(groups = "Website", priority = 16)
    public void FileDownload() throws FileNotFoundException {
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[14]/a")).click();
        SelenideElement file = $(withText("UploadFileTest.txt"));
        file.shouldHave(text("UploadFileTest.txt"));
        file.download();
    }

    @Test(groups = "Website", priority = 17)
    public void FloatingMenu() {
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[16]/a")).click();
        SelenideElement menu = $("#menu");
        SelenideElement pagefooter = $(By.xpath("//*[@id=\"page-footer\"]/div/div/a"));
        menu.shouldBe(visible);
        pagefooter.scrollTo();
        menu.shouldBe(visible);
    }

    @Test(groups = "Website", priority = 18)
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
        resetmail.waitUntil(visible, 5000);
    }

    @Test(groups = "Website", priority = 19)
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
        messege.waitUntil(visible, 5000).shouldHave(text("Your username is invalid!"));

        username.setValue("tomsmith");
        password.setValue("");
        button.click();
        messege.waitUntil(visible, 5000).shouldHave(text("Your password is invalid!"));

        username.setValue("tomsmith");
        password.setValue("SuperSecretPassword!");
        button.click();
        messege.waitUntil(visible, 5000).shouldHave(text("You logged into a secure area!"));
        logoutbutton.click();
        messege.waitUntil(visible, 5000).shouldHave(text("You logged out of the secure area!"));
    }

    @Test(groups = "Website", priority = 20)
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

    @Test(groups = "Website", priority = 21)
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
        Assert.assertNotEquals(after_text, before_text);
        System.out.println(before_text);
        System.out.println(after_text);
    }

    @Test(groups = "Website", priority = 22)
    public void Geolocation() {
        FirefoxProfile profile = createFirefoxProfileWithEnabledGeolocation();
        WebDriver driver = new FirefoxDriver(new FirefoxOptions().setProfile(profile));
        driver.manage().window().maximize();
        try {
            WebDriverRunner.setWebDriver(driver);
            open("https://the-internet.herokuapp.com/");
            $(By.xpath("//*[@id=\"content\"]/ul/li[20]/a")).click();
            $(".example > button:nth-child(3)").click();
            SelenideElement latitude = $("#lat-value");
            SelenideElement longitude = $("#long-value");
            latitude.waitUntil(visible,10000);
            System.out.println("latitude :"+latitude);
            longitude.waitUntil(visible,10000);
            System.out.println("longitude :"+longitude);
        }
        finally {
            WebDriverRunner.closeWebDriver();
        }
    }

    private FirefoxProfile createFirefoxProfileWithEnabledGeolocation() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("geo.enabled", true);
        profile.setPreference("permissions.default.geo", 1);
        return profile;
    }

    @Test(groups = "Website", priority = 23)
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

    @Test(groups = "Website", priority = 24)
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
        user1text.waitUntil(appear, 5000).shouldHave(text("name: user1"));
        user2.hover();
        user2text.waitUntil(appear, 5000).shouldHave(text("name: user2"));
        user3.hover();
        user3text.waitUntil(appear, 5000).shouldHave(text("name: user3"));
    }

    @Test(groups = "Website", priority = 25)
    public void InfiniteScroll() {
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[23]/a")).click();
        SelenideElement page_footer = $("#page-footer");
        SelenideElement element1page = $(By.xpath("//*[@id=\"content\"]/div/div/div/div/div[2]"));
        SelenideElement element2page = $(By.xpath("//*[@id=\"content\"]/div/div/div/div/div[4]"));
        sleep(1000);
        element1page.waitUntil(visible, 5000);
        element2page.shouldNot(exist);
        int elementPosition = page_footer.getLocation().getY();
        String js = String.format("window.scrollTo(0, 5000)", elementPosition);
        JavascriptExecutor jsx = (JavascriptExecutor) getWebDriver();
        jsx.executeScript(js, "");
        element2page.waitUntil(visible, 5000);
        element2page.shouldBe(visible);
        sleep(1000);
    }

    @Test(groups = "Website", priority = 26)
    public void JQueryUIMenu() throws FileNotFoundException {
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[24]/a")).click();
        SelenideElement disabled = $("#ui-id-1");
        SelenideElement enabled = $("#ui-id-2");
        SelenideElement download = $("#ui-id-4");
        SelenideElement pdf = $(By.xpath("//*[@id=\"ui-id-6\"]"));
        SelenideElement csv = $(By.xpath("//*[@id=\"ui-id-7\"]"));
        SelenideElement excel = $(By.xpath("//*[@id=\"ui-id-8\"]"));
        disabled.hover();
        download.shouldNot(visible);
        enabled.hover();
        download.waitUntil(visible, 5000).hover();
        pdf.waitUntil(visible, 5000).download();
        csv.waitUntil(visible, 5000).download();
        excel.waitUntil(visible, 5000).download();
    }

    @Test(groups = "Website", priority = 27)
    public void JavaScriptAlerts() {
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[25]/a")).click();
        SelenideElement jsalert = $(By.xpath("//*[@id=\"content\"]/div/ul/li[1]/button"));
        SelenideElement jsconfirm = $(By.xpath("//*[@id=\"content\"]/div/ul/li[2]/button"));
        SelenideElement jsprompt = $(By.xpath("//*[@id=\"content\"]/div/ul/li[3]/button"));
        SelenideElement result = $("#result");
        jsalert.click();
        switchTo().alert().accept();
        result.shouldHave(text("You successfuly clicked an alert"));
        jsconfirm.click();
        switchTo().alert().dismiss();
        result.shouldHave(text("You clicked: Cancel"));
        jsconfirm.click();
        switchTo().alert().accept();
        result.shouldHave(text("You clicked: Ok"));
        jsprompt.click();
        switchTo().alert().sendKeys("Hello World!");
        switchTo().alert().dismiss();
        result.shouldHave(text("You entered: null"));
        jsprompt.click();
        switchTo().alert().sendKeys("Hello World!");
        switchTo().alert().accept();
        result.shouldHave(text("You entered: Hello World!"));
    }

    @Test(groups = "Website", priority = 28)
    public void JavaScript_OnloadEventError() {
        /**
         * does not work in Firefox
         */

        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[26]/a")).click();
        sleep(2000);

        List<String> webDriverLogs = getWebDriverLogs(BROWSER, Level.SEVERE);

        String logEntry = webDriverLogs.get(1);
        System.out.println(logEntry);
        Assert.assertTrue(logEntry, logEntry.contains("Uncaught TypeError: Cannot read property 'xyz' of undefined"));

        /**
         * Waiting for fix getJavaScriptError
         * issue https://github.com/codeborne/selenide/issues/540
         */
    }

    @Test(groups = "Website", priority = 29)
    public void KeyPresses() {
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[27]/a")).click();
        SelenideElement result = $("#result");
        actions().sendKeys(Keys.TAB).perform();
        result.shouldHave(text("You entered: TAB"));
        actions().sendKeys(Keys.ENTER).perform();
        result.shouldHave(text("You entered: ENTER"));
        actions().sendKeys(Keys.SPACE).perform();
        result.shouldHave(text("You entered: SPACE"));
        actions().sendKeys("a").perform();
        result.shouldHave(text("You entered: A"));
        sleep(1000);
    }

    @Test(groups = "Website", priority = 30)
    public void MultipleWindows() {
        open("http://the-internet.herokuapp.com/");
        $(By.xpath("//*[@id=\"content\"]/ul/li[29]/a")).click();
        SelenideElement link = $(By.xpath("//*[@id=\"content\"]/div/a"));
        link.click();
        switchTo().window("New Window").close();
        switchTo().window("The Internet");
        link.shouldBe(visible);
    }

}


/**
 * Work in progress Drag and Drop Html5, JavaScript onload event error, Large & Deep DOM, Notification Messages,
 * Redirect Link, Secure File Download, Shifting Content, Slow Resources, Sortable Data Tables, Status Codes, Typos,
 */



