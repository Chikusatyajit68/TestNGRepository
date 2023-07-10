package com.nopcommerce.pages;

import com.nopcommerce.utills.ReadLocator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

public class Loginpage  {
    private WebDriver driver;
    public Loginpage (WebDriver driver)
    {
        this.driver=driver;
    }
    public WebElement getElememnt(String locatorname) throws IOException {
        ReadLocator readLocator=new ReadLocator("LP");
              String locatorValue     = readLocator.getLocator(locatorname);
           By locator=            By.xpath(locatorValue);
          WebElement element=        driver.findElement(locator);
          return element;
    }
    public void CLEARTEXTBOX(WebElement element)
    {
        element.clear();

    }
    public void SETTEXTINTEXTBOX(WebElement element, String data)
    {
        element.sendKeys(data);
    }
    public  void CLICKONLOGINBTN(WebElement element)
    {
        element.click();
    }
    public String GETTEXT( WebElement element)
    {
      return   element.getText();
    }
    public String GETATTRIBUTE( WebElement element)
    {
        return   element.getAttribute("innerHTML");
    }
    public  void clearEmailTextbox() throws IOException {
         WebElement emailTextBox=          getElememnt("ETBL");
         CLEARTEXTBOX(emailTextBox);
    }
    public  void setTextinEmailTextbox( String email) throws IOException {
        WebElement emailTextBox=          getElememnt("ETBL");
        SETTEXTINTEXTBOX(emailTextBox,email);
    }
    public void clickOnLoginBtn() throws IOException {
      WebElement loginBtn=               getElememnt("LOGINL");
      CLICKONLOGINBTN(loginBtn);
    }

    public String getErrorMsgLabelatEmailTextBox() throws IOException {
     WebElement errorMsgLabel=   getElememnt("ERRORMSGLABELATEMAILTEXTBOXL");
          return GETTEXT(errorMsgLabel);

    }

    public  String getInvalidloginErrorMsglabel() throws IOException {
        WebElement invalidloginErrorMsglabel=   getElememnt("INVLGNERORMSGL");
     return    GETATTRIBUTE(invalidloginErrorMsglabel);
    }
    public  void clearPasswordTextbox() throws IOException {
        WebElement passwordTextbox=          getElememnt("PTBL");
        CLEARTEXTBOX(passwordTextbox);
    }
    public  void setTextinPasswordTextbox( String pass) throws IOException {
        WebElement passwordTextbox = getElememnt("PTBL");
        SETTEXTINTEXTBOX(passwordTextbox, pass);
    }

}
