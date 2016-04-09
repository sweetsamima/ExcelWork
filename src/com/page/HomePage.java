package com.page;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class HomePage {
	
	WebDriver driver;
	Actions act;
    Select dropdown;
	
	public HomePage(WebDriver a){
		driver=a;
		
	}

	


	By brute = By.xpath(PropertiesUtil.getProperty("brute"));
		
    By feature = By.xpath(PropertiesUtil.getProperty("feature"));
    By QuickView = By.xpath(PropertiesUtil.getProperty("QuickView"));
	By addQtty = By.xpath(PropertiesUtil.getProperty("addQtty"));
	By size = By.xpath(PropertiesUtil.getProperty("size"));
	By color = By.xpath(PropertiesUtil.getProperty("color"));
	By addToCart = By.xpath(PropertiesUtil.getProperty("addToCart"));
	By cost = By.xpath(PropertiesUtil.getProperty("cost"));
	By prossedToCheck = By.xpath(PropertiesUtil.getProperty("prossedToCheck"));
	By backBtn = By.xpath(PropertiesUtil.getProperty("backBtn"));
		
	By signIn = By.className(PropertiesUtil.getProperty("signIn"));
	
	public boolean getEvery(){
		
		return driver.findElement(brute).isEnabled();
		
	}
	
	public void SignIn(){
		driver.findElement(signIn).click();
	}
	
   public double Feature(){
	   act= new Actions(driver);
	   act.moveToElement(driver.findElement(feature)).moveToElement(driver.findElement(QuickView)).click().build().perform();
	   
	   driver.switchTo().frame(driver.findElement(By.className("fancybox-iframe")));
	   
	   driver.findElement(addQtty).click();
	  //  driver.findElement(size).click();
	    dropdown = new Select(driver.findElement(size));
	    dropdown.selectByVisibleText("M");
	   
	    driver.findElement(color).click();
	    
        String price = driver.findElement(cost).getText();
        //Price returns $55.51 which is String
	     driver.findElement(addToCart).click();
	        //Removes $ sign from String price which will be 55.51 but still its a String
	    String b = price.substring(1);
	        //Converts value of String b into Double
	        double v = Double.parseDouble(b);
	        double tot = (v*2)+2;
	       return tot;
	        
	        
	      // return (Double.parseDouble(price.substring(1))*2)+2;
	        
	   
	    }
   
   public void Prossed(){
	    driver.switchTo().defaultContent();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	     driver.findElement(prossedToCheck).click();
	     driver.navigate().to("http://ebfs.bruteforcesolution.net/ebfs/index.php?controller=order");
	     
   }
   
   
}
