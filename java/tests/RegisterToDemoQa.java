package tests;


import org.testng.annotations.DataProvider;
public class RegisterToDemoQa {
    @DataProvider(name="RegisterToDemoQA")
    public static Object[][] getDataFromDataprovider(){
        return new Object[][] {
                {"","","","","","",""},
                {"TEST","AUTOMATYCZNY","123","TESTAUTOMATYCZNY123","TESTAUTOMATYCZNY@TESTAUTOMATYCZNY","TEST","TEST1234"},
                {"TEST","AUTOMATYCZNY","1234567890","TESTAUTOMATYCZNY1234","TESTAUTOMATYCZNY@TESTAUTOMATYCZNY1234.com","TEST1234","TEST1234"},
        };
    }}