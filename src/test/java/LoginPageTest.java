import com.nopcommerce.pages.Dashboardpage;
import com.nopcommerce.pages.Loginpage;
import com.nopcommerce.utills.Browser;
import com.nopcommerce.utills.Dataconfigconstants;
import com.nopcommerce.utills.ReadData;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class LoginPageTest {
    private WebDriver driver;
    Loginpage loginpage;
    ReadData loginData;

    @BeforeMethod
    public void setup() throws IOException {
        //  ReadData readData=new ReadData("browserpage") ;
        //    List<String >browserinfo = readData.getData(1);
        //    String browsername=browserinfo.get(0);
        //    String url=browserinfo.get(1);
        driver = Browser.LaunchBrowser();
        Browser.openUrl();


    }

    @AfterMethod
    public void tearDown() {
        Browser.closeBrowser();
    }

    @Test(enabled = true,priority = 0)
    public void verifyloginBtnFunctionalitywithEmailasBlank() throws IOException {
        loginpage = new Loginpage(driver);

        loginpage.clearEmailTextbox();
        loginpage.clickOnLoginBtn();
        String actualErrorMsg = loginpage.getErrorMsgLabelatEmailTextBox();
        Assert.assertEquals(actualErrorMsg, Dataconfigconstants.EMAILBLANKERRORMSG);

    }

    @Test(enabled = true,priority = 1)
    public void verifyloginBtnFunctionalitywithInvalidEmailformat() throws IOException {
        loginData = new ReadData("loginpage");
        loginpage = new Loginpage(driver);
        String invailddata = loginData.getData(8, 0);
        loginpage.clearEmailTextbox();
        loginpage.setTextinEmailTextbox(invailddata);
        loginpage.clickOnLoginBtn();
        String actualErrorMsg = loginpage.getErrorMsgLabelatEmailTextBox();
        Assert.assertEquals(actualErrorMsg, Dataconfigconstants.INVALIDERRORMASG);

    }

    @Test(enabled = true,priority = 2)
    public void verifyinvalidloginErroaMsglabel() throws IOException, InterruptedException {
        loginData = new ReadData("loginpage");
        loginpage = new Loginpage(driver);
        String invailddata = loginData.getData(9, 0);
        Thread.sleep(2000);
        loginpage.clearEmailTextbox();
        Thread.sleep(2000);
        loginpage.setTextinEmailTextbox(invailddata);
        loginpage.clickOnLoginBtn();
        Thread.sleep(2000);
        String actualErrorMsg = loginpage.getInvalidloginErrorMsglabel();
        System.out.println(actualErrorMsg);
        Assert.assertTrue(actualErrorMsg.contains(Dataconfigconstants.INVALIDLOGINERRORMSG1));

    }

    @Test(enabled = true,priority = 3)
    public void verifyloginBtnCunctionalitywithvalidemail() throws IOException, InterruptedException {
        loginData = new ReadData("loginpage");
        loginpage = new Loginpage(driver);
        String invailddata = loginData.getData(1, 0);
        Thread.sleep(2000);
        loginpage.clearEmailTextbox();
        Thread.sleep(2000);
        loginpage.setTextinEmailTextbox(invailddata);
        loginpage.clickOnLoginBtn();
        Thread.sleep(2000);
        Dashboardpage dashboardpage = new Dashboardpage(driver);
        Assert.assertTrue(dashboardpage.isDashboardvisible(), "Test script failed as dash board is not visible");


    }
  @DataProvider(name="logincredentials")
    public Object[][] getData() throws IOException {
        loginData=new ReadData("loginpage");
       return loginData.getData();
    }
    @Test(enabled = true,dataProvider = "logincredentials" ,priority = 4)
    public void verifyLoginBtnfunctionalityWithsetofcredentials(String eamil, String pass) throws IOException, InterruptedException {
        loginpage  =new Loginpage(driver);
        Dashboardpage Dashbaordpage=new Dashboardpage(driver);

        loginpage.clearEmailTextbox();


        loginpage.setTextinEmailTextbox(eamil);

        loginpage.clearPasswordTextbox();

        loginpage.setTextinPasswordTextbox(pass);

        loginpage.clickOnLoginBtn();

        Assert.assertTrue(Dashbaordpage.isDashboardvisible(),"Testcase failed as dashboard page not visible");
    }
}