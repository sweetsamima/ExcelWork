package com.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignIn {
	WebDriver driver;
	
public SignIn(WebDriver a){
	driver=a;
}

 By email = By.id(PropertiesUtil.getProperty("email"));
 By pass = By.id(PropertiesUtil.getProperty("pass"));
 By submit = By.id(PropertiesUtil.getProperty("submit"));
 
 By create = By.id(PropertiesUtil.getProperty("create"));
 By createBtn = By.id(PropertiesUtil.getProperty("createBtn"));
 By errorMsg = By.xpath(PropertiesUtil.getProperty("errorMsg"));
 
 public String TitleText(){
	 return driver.getTitle();
 }
 
 public void Invalid(String uname){
	 driver.findElement(create).sendKeys(uname);
	 driver.findElement(submit).click();
 }
 
 public String SignUpErrorMessage(){
	(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(errorMsg));
	 return driver.findElement(errorMsg).getText();
 }
 
 public String color(){
	return driver.findElement(create).getCssValue("background-color");
 }
 
 public void SignInAct(String emails, String password){
	 driver.findElement(email).sendKeys(emails);
	 driver.findElement(pass).sendKeys(password);
	 driver.findElement(submit).click();
 }

	
	
}

