package tests;

import com.codeborne.selenide.Condition;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.AutoMationPracticePage;
import pages.SignUpPage;

/**
 * 4 diffrent wy to use parameters
 */

public class Exercise3 extends Setup {

    /**
     * By using DataProvider
     */
    @Test(dataProvider = "RegisterToDemoQA",groups = "Website", priority = 1)
    public void SignUpTestWithDataProviderInTest (String name,String surname,String tel, String user,String mail,String pass,String cpass){
        SignUpPage signUpPage = new SignUpPage();
        signUpPage.open();
        signUpPage.status.click();
        signUpPage.hobby.click();
        signUpPage.login(name,surname,tel,user,mail,pass,cpass);
        signUpPage.submit();
        signUpPage.successmessege.shouldHave(Condition.text("Thank you for your registration"));
    }
    @DataProvider(name="RegisterToDemoQA")
    public Object[][] getDataFromDataprovider() {
        return new Object[][]{
                {"","","","","","",""},
                {"TEST","AUTOMATYCZNY","123","TESTAUTOMATYCZNY123","TESTAUTOMATYCZNY@TESTAUTOMATYCZNY","TEST","TEST1234"},
                {"TEST","AUTOMATYCZNY","1234567890","TESTAUTOMATYCZNY123","TESTAUTOMATYCZNY@TESTAUTOMATYCZNY123.com","TEST1234","TEST1234"},
        };
    }

    /**
     * By using DataProvider from file ReqisterToDemoQa
     */

    @Test(dataProvider = "RegisterToDemoQA",dataProviderClass= RegisterToDemoQa.class,groups = "Website", priority = 2)
    public void SignUpTestWithDataProviderSecondMethod (String name,String surname,String tel, String user,String mail,String pass,String cpass){
        SignUpPage signUpPage = new SignUpPage();
        signUpPage.open();
        signUpPage.status.click();
        signUpPage.hobby.click();
        signUpPage.login(name,surname,tel,user,mail,pass,cpass);
        signUpPage.submit();
        signUpPage.successmessege.shouldHave(Condition.text("Thank you for your registration"));
    }

    /**
     * By using parameters from xml
     */

    @Test(groups = "Website", priority = 3)
    @Parameters({"name","surname","tel","user","mail","pass","cpas"})
    public void SignUpTestWithParametsFromXML (String name,String surname,String tel, String user,String mail,String pass,String cpass){
        SignUpPage signUpPage = new SignUpPage();
        signUpPage.open();
        signUpPage.status.click();
        signUpPage.hobby.click();
        signUpPage.login(name,surname,tel,user,mail,pass,cpass);
        signUpPage.submit();
        signUpPage.successmessege.shouldHave(Condition.text("Thank you for your registration"));
    }

    /**
     * By using multiple values from xlm
     */
    @Test(dataProvider = "getData",groups = "Website", priority = 4)
    public void testMethod(String mail) {
        AutoMationPracticePage autoMationPracticePage = new AutoMationPracticePage();
        autoMationPracticePage.open();
        autoMationPracticePage.registmailtogetnewsletter(mail);
        autoMationPracticePage.errormessege.shouldHave(Condition.text("Newsletter : This email address is already registered."));
    }

    @DataProvider
    public Object[][] getData(ITestContext context) {
        String parameter = context.getCurrentXmlTest().getLocalParameters().get("mails");
        String[] names = parameter.split(",");
        Object[][] returnValues = new Object[names.length][1];
        int index = 0;
        for (Object[] each : returnValues) {
            each[0] = names[index++].trim();
        }
        return returnValues;
    }

}
