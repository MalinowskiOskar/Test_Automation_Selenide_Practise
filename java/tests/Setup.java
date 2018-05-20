package tests;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import static com.codeborne.selenide.Selenide.close;

public class Setup {

    @BeforeSuite(groups = {"Website"})
    public void setup() {
        Configuration.startMaximized = true;
        Configuration.browser = "firefox";
        System.setProperty("webdriver.gecko.driver","C:\\important\\geckodriver\\geckodriver.exe");
    }

    @AfterSuite(groups = {"Website"})

    public void cleanUp() {
        System.out.println("\nEnd of tests!\n");
        close();
    }
}

