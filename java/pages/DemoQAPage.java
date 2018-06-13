package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class DemoQAPage {

    public DemoQAPage open() {
        Selenide.open("http://demoqa.com");
        return this;
    }
    public SignUpPage goToSignUpPage(){
        signupBtn.click();
        return  new SignUpPage();
    }
    public DraggablePage goToDraggablePage(){
        draggableBtn.click();
        return  new DraggablePage();
    }

    SelenideElement signupBtn = $("#menu-item-374");
    SelenideElement draggableBtn = $("#menu-item-140 > a");
}

