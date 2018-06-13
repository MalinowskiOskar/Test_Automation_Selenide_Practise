package tests;

import org.openqa.selenium.Point;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DemoQAPage;
import pages.DraggablePage;

import static com.codeborne.selenide.Condition.visible;

public class Exercise5 extends Setup {

    @Test(groups = "Website", priority = 1)
    public void gotoDraggablePage() {
        DemoQAPage demoQAPage = new DemoQAPage();
        demoQAPage.open();
        demoQAPage.goToDraggablePage();
    }

    @Test(groups = "Website", priority = 2)
    public void DragMeAround() {
        DraggablePage draggablePage = new DraggablePage();
        draggablePage.waitforvisible.waitUntil(visible,5000);

        Point point = draggablePage.dragme.getLocation();
        /**
         * START ELEMENT POSITION 575,340
         */
        Assert.assertEquals(point,new Point(575, 340));
        draggablePage.move(draggablePage.dragme,400,200);

        Point point1 = draggablePage.dragme.getLocation();
        Assert.assertEquals(point1,new Point(975, 540));
        draggablePage.move(draggablePage.dragme,-400,0);

        Point point2 = draggablePage.dragme.getLocation();
        Assert.assertEquals(point2,new Point(575, 540));
        draggablePage.move(draggablePage.dragme,400,-200);

        Point point3 = draggablePage.dragme.getLocation();
        Assert.assertEquals(point3,new Point(975, 340));
    }

    @Test(groups = "Website", priority = 3)
    public void ConstrainMovement() {
        DraggablePage draggablePage = new DraggablePage();
        draggablePage.gotoContainMovement();

        /**
         * This Element can be dragged only vertically
         */
        Point point = draggablePage.draggable1.getLocation();
        Assert.assertEquals(point,new Point(575, 396));
        draggablePage.move(draggablePage.draggable1,200,200);
        Point point1 = draggablePage.draggable1.getLocation();
        Assert.assertEquals(point1,new Point(575, 596));

        /**
         * This Element can be dragged only horizontally
         */
        Point point2 = draggablePage.draggable2.getLocation();
        Assert.assertEquals(point2,new Point(675, 396));
        draggablePage.move(draggablePage.draggable2,200,200);
        Point point3 = draggablePage.draggable2.getLocation();
        Assert.assertEquals(point3,new Point(875, 396));


        /**
         * This Element can be dragged only in the box
         */
        Point point4 = draggablePage.draggable3.getLocation();
        Assert.assertEquals(point4,new Point(587, 554));
        draggablePage.move(draggablePage.draggable3,600,100);
        Point point5 = draggablePage.draggable3.getLocation();
        Assert.assertEquals(point5,new Point(1090, 580));

        /**
         * This Element is contained within parent
         */
//        Point point6 = draggablePage.draggable4.getLocation();
//        Assert.assertEquals(point6,new Point(696, 563));
//        draggablePage.move(draggablePage.draggable4,-200,0);
//        Point point7 = draggablePage.draggable4.getLocation();
//        Assert.assertEquals(point7,new Point(696, 562));
//        /**
//         * W.I.P
//         */
    }

    @Test(groups = "Website", priority = 4)
    public void CursorStyle() {
        DraggablePage draggablePage = new DraggablePage();
        draggablePage.gotoCursorStyle();

        
    }

}
