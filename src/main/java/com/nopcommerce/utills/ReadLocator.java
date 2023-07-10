package com.nopcommerce.utills;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadLocator {
 private    FileInputStream inputStream;
 private Properties pro;

 public  ReadLocator(String filename) throws IOException {
     String filepath=".\\src\\main\\resources\\locators\\"+filename+".properties";
     inputStream=new FileInputStream(filepath);
     pro=new Properties();
     pro.load(inputStream);
 }
 public String getLocator(String locatorname)
 {
      return   pro.getProperty(locatorname);
 }
}
