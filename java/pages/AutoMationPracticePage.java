package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class AutoMationPracticePage {

    public AutoMationPracticePage open() {
        Selenide.open("http://automationpractice.com/index.php");
        return this;
    }

    public SelenideElement newletter = $(By.xpath("//*[@id=\"newsletter-input\"]"));
    public SelenideElement errormessege = $(By.xpath("//*[@id=\"columns\"]/p"));
    public SelenideElement submit = $(By.xpath("//*[@id=\"newsletter_block_left\"]/div/form/div/button"));

    public void registmailtogetnewsletter(String mail) {
        newletter.setValue(mail);
        submit.click();
    }
}
