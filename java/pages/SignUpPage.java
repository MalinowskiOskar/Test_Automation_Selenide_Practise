package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SignUpPage {

public SignUpPage open() {
        Selenide.open("http://demoqa.com/registration/");
        return this;
    }

    public SelenideElement successmessege = $(By.xpath("//*[@id=\"post-49\"]/div/p"));
    public SelenideElement status=$(By.xpath("//*[@id=\"pie_register\"]/li[2]/div/div/input[1]"));
    public SelenideElement hobby=$(By.xpath("//*[@id=\"pie_register\"]/li[3]/div/div[1]/input[2]"));
    public SelenideElement waitforvisible = $("#name_3_firstname");
    public SelenideElement messege = $(By.xpath("/html/body/div[1]/div/div[1]/main/article/div/p"));
    public SelenideElement submitBtn = $(By.xpath("//*[@id=\"pie_register\"]/li[14]/div/input"));

    public static SelenideElement
            firstName = $("#name_3_firstname"),
            lastName = $("#name_3_lastname"),
            phone = $("#phone_9"),
            userName = $("#username"),
            eMail = $("#email_1"),
            password = $("#password_2"),
//            submitBtn = $(By.xpath("//*[@id=\"pie_register\"]/li[14]/div/input")),
            confirmPassword = $("#confirm_password_password_2");


    public void login(String name, String surname, String tel, String user, String mail, String pass, String cpass) {
        firstName.clear();
        firstName.setValue(name);
        lastName.clear();
        lastName.setValue(surname);
        phone.clear();
        phone.setValue(tel);
        userName.clear();
        userName.setValue(user);
        eMail.clear();
        eMail.setValue(mail);
        password.clear();
        password.setValue(pass);
        confirmPassword.clear();
        confirmPassword.setValue(cpass);
    }

    public SignUpPage submit() {
        submitBtn.click();
        return new SignUpPage();
    }

}
