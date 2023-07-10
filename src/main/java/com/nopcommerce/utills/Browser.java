package com.nopcommerce.utills;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Browser {
   private static   WebDriver driver;
   public  Browser(WebDriver driver)
   {
       this.driver=driver;
   }
   public static WebDriver LaunchBrowser()
   {
       driver=new  ChromeDriver();
       return driver;
   }
       public static  void openUrl( )
       {
           driver.get("https://admin-demo.nopcommerce.com/login");
       }
       public static    void closeBrowser()
       {
           driver.close();
       }



}
