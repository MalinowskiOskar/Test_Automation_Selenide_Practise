package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;

public class DraggablePage {

    public SelenideElement waitforvisible = $(By.xpath("//*[@id=\"ui-id-1\"]"));
    public SelenideElement dragme = $("#draggable");
    public SelenideElement ConstrainMovementBtn = $(By.xpath("//*[@id=\"ui-id-2\"]"));
    public SelenideElement CursorStyleBtn = $(By.xpath("//*[@id=\"ui-id-3\"]"));


    public SelenideElement draggable1 = $("#draggabl"),
            draggable2 = $("#draggabl2"),
            draggable3 = $("#draggabl3"),
            draggable4 = $("#draggabl5");


    public void move(WebElement element, int offsetX, int offsetY){
        actions().clickAndHold(element).moveByOffset(offsetX,offsetY).release(element).build().perform();
    }
    public void gotoContainMovement (){
        ConstrainMovementBtn.click();
    }

    public void gotoCursorStyle (){
        CursorStyleBtn.click();
    }


}
