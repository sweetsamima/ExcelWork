package com.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage {
	 WebDriver driver;
	 public AccountPage(WebDriver a){
		 driver=a;
	 }

	 
	 By accholder = By.xpath(".//*[@id='header']/div[2]/div/div/nav/div[1]/a/span");
	 By signout = By.className("logout");
	 
	 
	 	public String Accountttylverify(){
	 		return driver.getTitle();
	 	}
	 	
	 	public String Accverify(){
	 		return driver.findElement(accholder).getText();
	 	}
	 	
	 	public void LogOutClick(){
	 		driver.findElement(signout).click();
	 	}
}
