import com.nopcommerce.pages.Dashboardpage;
import com.nopcommerce.pages.Loginpage;
import com.nopcommerce.utills.Browser;
import com.nopcommerce.utills.ReadData;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class DashboardTest {

    private WebDriver driver;
    ReadData readData;
    Loginpage loginpage;
    Dashboardpage dashboardpage;

    @BeforeMethod
    public void setUp() {
        driver = Browser.LaunchBrowser();
        Browser.openUrl();
    }

    @AfterMethod
    public void tearDown() {
        Browser.closeBrowser();
    }

    @Test(enabled = true)
    public void verifyLoginBtnFunctionalitywithValidEmail() throws IOException {
        readData = new ReadData("loginpage");
        String vaiiddata = readData.getData(1, 0);
        loginpage = new Loginpage(driver);
        loginpage.clearEmailTextbox();
        loginpage.setTextinEmailTextbox(vaiiddata);
        loginpage.clickOnLoginBtn();
        dashboardpage = new Dashboardpage(driver);
        Assert.assertTrue(dashboardpage.isDashboardvisible(), "Test failed as dashbaoard is not visible");
    }

    @Test( enabled = true,dependsOnMethods = "verifyLoginBtnFunctionalitywithValidEmail")
    public void verifySearchBoxFunctionalityWithInvaliddata() throws Exception {
        readData = new ReadData("loginpage");
        String vaiiddata = readData.getData(1, 0);
        loginpage = new Loginpage(driver);
        loginpage.clearEmailTextbox();
        loginpage.setTextinEmailTextbox(vaiiddata);
        loginpage.clickOnLoginBtn();
        dashboardpage = new Dashboardpage(driver);
        readData = new ReadData("dashboardpage");
        String invaliddata = readData.getData(1, 1);
        dashboardpage.setTextInSearchBox(invaliddata);
        Assert.assertTrue(dashboardpage.isNoResultVisible(), "test script failrd as no result is visible");
    }
    @Test(enabled = true)

    public void verifySearchBoxFunctionalityWithvaliddata() throws Exception {
        readData = new ReadData("loginpage");
        String validdata = readData.getData(1, 0);
        loginpage = new Loginpage(driver);
        loginpage.clearEmailTextbox();
        loginpage.setTextinEmailTextbox(validdata);
        loginpage.clickOnLoginBtn();
        dashboardpage = new Dashboardpage(driver);
        readData = new ReadData("dashboardpage");
        String dbvaliddata = readData.getData(1, 0);
        dashboardpage.setTextInSearchBox(dbvaliddata);
        List<String> suggesteddata=dashboardpage.getAlloptionsValueFromSuggestedList();
        boolean status=dashboardpage.issuggestedListContainsdata(dbvaliddata);
        Assert.assertEquals(suggesteddata.size(),1);
    }
    @Test(enabled = true)

    public void verifySearchBoxFunctionalityWithPartialdata() throws Exception {
        readData = new ReadData("loginpage");
        String vaiiddata = readData.getData(1, 0);
        loginpage = new Loginpage(driver);
        loginpage.clearEmailTextbox();
        loginpage.setTextinEmailTextbox(vaiiddata);
        loginpage.clickOnLoginBtn();
        dashboardpage = new Dashboardpage(driver);
        readData = new ReadData("dashboardpage");
        String partialdata = readData.getData(1, 2);
        dashboardpage.setTextInSearchBox(partialdata);
       // Assert.assertTrue(dashboardpage.issuggestedListContainsdata(partialdata), "Test script faled as validdata not found");
      //  Assert.assertEquals(dashboardpage.suggestedValues.size(),1);
        List<String> suggesteddata=dashboardpage.getAlloptionsValueFromSuggestedList();
        boolean status=dashboardpage.issuggestedListContainsdata(partialdata);
        Assert.assertEquals(suggesteddata.size(),8);
    }
}