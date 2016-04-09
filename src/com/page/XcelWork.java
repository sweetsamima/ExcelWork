package com.page;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class XcelWork {
	WebDriver driver;
	Xls_Reader Testcase=new Xls_Reader(PropertiesUtil.getProperty("TestData"));
	Xls_Reader Result=new Xls_Reader(PropertiesUtil.getProperty("TestResult"));
	HomePage Home;
	SignIn Sign;
	AccountPage Account;
	
  @Test(enabled=false)
  public void InvalidSignUp() {
	  int count = Testcase.rowcnt(PropertiesUtil.getProperty("InvalidSignUp"));
	  
	  for(int c=2;c<=count;c++){
		
		  driver.get(PropertiesUtil.getProperty("TestURL"));
		  
		  driver.manage().window().maximize(); 
		  
	  String uname = Testcase.getCellData("Sheet1","Email",c);
	  System.out.println(uname);
	  
	 Home = new HomePage(driver);
	 Home.SignIn();
	 Sign = new SignIn(driver);
	 Sign.Invalid(uname);
	 String colors= Sign.color();
	 String colorss = Testcase.getCellData("Sheet1","Expected",c);
	 
	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 
	 
	 
	 String ertext = Sign.SignUpErrorMessage();
	 String exptext = Testcase.getCellData("Sheet1","ErrorMessage" , c);
	 Assert.assertEquals(ertext, exptext);
	 
	 int columnnum = Testcase.getcollam("Sheet1", "Status");
	 int columnnu = Testcase.getcollam("Sheet1", "Actual");
	 int errcol = Testcase.getcollam("Sheet1", "ActualErrorMessage");
	 
	 if (colors.equals(colorss)){
		System.out.println(colors);
		Testcase.setResultData("Sheet1",columnnu, c,colors );
		Testcase.setResultData("Sheet1", errcol, c, ertext);
		Testcase.setResultData("Sheet1",columnnum, c, "PASS");
		
		
	 }else{
		System.out.println(colors);
		Testcase.setResultData("Sheet1",columnnu, c,colors );
		Testcase.setResultData("Sheet1", errcol, c, ertext);
		Testcase.setResultData("Sheet1",columnnum, c, "FAIL");
		
	}
	 
	  } 
  }
  
  @Test
  public void LogIn(){
	  
	  int count = Testcase.rowcnt(PropertiesUtil.getProperty("LogIn"));
	  for(int a=2;a<=count;a++){
		  
		  driver.get(PropertiesUtil.getProperty("TestURL"));
		  
		  driver.manage().window().maximize(); 
		  
		  String emails=Testcase.getCellData(PropertiesUtil.getProperty("LogIn"),Testcase.getcollam(PropertiesUtil.getProperty("LogIn"),"username"),a);
		  String password=Testcase.getCellData(PropertiesUtil.getProperty("LogIn"),Testcase.getcollam(PropertiesUtil.getProperty("LogIn"),"password"),a);
		  String Accname=Testcase.getCellData(PropertiesUtil.getProperty("LogIn"),Testcase.getcollam(PropertiesUtil.getProperty("LogIn"),"name"),a);
		  
		  System.out.println(emails);
		  System.out.println(password);
		  System.out.println(Accname);
		  
	 Home=new HomePage(driver);
	 Home.SignIn();
	 Sign=new SignIn(driver);
	 Sign.SignInAct(emails, password);
	 Account = new AccountPage(driver);
	 String actname = Account.Accverify();
	 Account.LogOutClick();
	 int statuscolumn=Testcase.getcollam(PropertiesUtil.getProperty("LogIn"),"status");
	 if (actname.equalsIgnoreCase(Accname)){
		 
		 Testcase.setResultData(PropertiesUtil.getProperty("LogIn"),statuscolumn,a, "PASS");
		 
	 }
	 else{
		 Testcase.setResultData(PropertiesUtil.getProperty("LogIn"),statuscolumn,a, "FAIL");
	 }
	  }
	  
  }
  
  @BeforeTest
  public void beforeTest() {
		
	  String browser = PropertiesUtil.getProperty("Browser");
	  
	  if(browser.equalsIgnoreCase("Firefox")){
		  driver = new FirefoxDriver();  
	  }else if(browser.equalsIgnoreCase("Chrome")){
		  System.setProperty("webdriver.chrome.driver", PropertiesUtil.getProperty("chromeDriver"));
		  driver = new ChromeDriver();
	  }else if(browser.equalsIgnoreCase("IE")){
		  System.setProperty("webdriver.ie.driver", PropertiesUtil.getProperty("ieDriver"));
		  driver = new InternetExplorerDriver();
	  }else{
		  System.out.println("This Framework Does not support that Browser");
	  }
  
  }
  @AfterTest
  public void afterTest() {
  driver.close();
  }

}
