package com.nopcommerce.pages;

import com.nopcommerce.utills.ReadLocator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Dashboardpage {
    private WebDriver driver;
 public    List<String> suggestedValues;

    public Dashboardpage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getElement(String locatorname) throws IOException {
        ReadLocator readLocator = new ReadLocator("DB");
        String locatorValue = readLocator.getLocator(locatorname);
        By locator = By.xpath(locatorValue);
        WebElement element = driver.findElement(locator);
        return element;
    }

    public List<WebElement> getElements(String locatorname) throws IOException {
        ReadLocator readLocator = new ReadLocator("DB");
        String locatorValue = readLocator.getLocator(locatorname);
        By locator = By.xpath(locatorValue);
        return driver.findElements(locator);

    }

    public boolean isDashboardvisible() throws IOException {
        WebElement dashboardlabel = getElement("DBl");
        return dashboardlabel.isDisplayed();

    }

    public void setTextInSearchBox(String searchData) throws Exception {
        WebElement searchBox = getElement("SBL");

        searchBox.sendKeys (Keys.chord(Keys.CONTROL,"a")  ,searchData);

    }

    public boolean isNoResultVisible() throws Exception {
        WebElement noresults = getElement("NRSL");
        return noresults.isDisplayed();
    }

    public List<String> getAlloptionsValueFromSuggestedList() throws IOException {
        List<WebElement> suggestedList = getElements("SUGLL");
        List<String> suggestedValues = new ArrayList<>();
        for (WebElement element : suggestedList) {
            suggestedValues.add(element.getText());
        }
        System.out.println(suggestedValues);
        return suggestedValues;
    }

    public boolean issuggestedListContainsdata(String Searchdta) throws IOException {
        suggestedValues = getAlloptionsValueFromSuggestedList();
      //  return suggestedValues.contains(Searchdta.toUpperCase());
        boolean status=true;
        for (String value:suggestedValues) {
            if (!suggestedValues.contains(Searchdta.toLowerCase()));
            {
                status = false;
                break;
            }
        }
        return  status;
    }
}
