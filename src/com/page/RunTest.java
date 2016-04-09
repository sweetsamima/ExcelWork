package com.page;

import org.testng.annotations.Test;
import com.page.Utitty;
import com.page.Utility;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class RunTest {
	WebDriver driver;
	ExtentReports report;
	ExtentTest logger;
	HomePage home;
	SummaryPage summary;
	SignIn sign;
	AccountPage account;
	
	
		
	
	
	@Test
	public void AccountVerify(){
		//Open/ Locate Excel file
		FileInputStream importfile;
		try {
			importfile = new FileInputStream("C:\\Users\\Samima\\Desktop\\BruteForceSolutions\\TestData.xls");
	
		//Open Excel Workbook
		HSSFWorkbook workbook;
		try {
			workbook = new HSSFWorkbook(importfile);

		//Open Work Sheet
		HSSFSheet worksheet = workbook.getSheet("userdata");
		//Count number of user data set or number of rows
		int rowcount = worksheet.getLastRowNum();
		// Start at row 1 as row 0 is our header row
		for(int count = 1; count<=rowcount; count++){
			//Getting row data
			HSSFRow row = worksheet.getRow(count);
			//Getting data and assigning to variables
			String useremail = row.getCell(0).toString();
			String userpass = row.getCell(1).toString();
			String username = row.getCell(2).toString();	
		
		logger=report.startTest("User Log In and Verify Account Name for: "+username);
		home = new HomePage(driver);
		logger.log(LogStatus.INFO, "Navigated to Home Page");
		home.SignIn();
		logger.log(LogStatus.INFO, "Sign In Link Clicked");
		sign = new SignIn(driver);
		Assert.assertEquals(sign.TitleText(),"Login - eBFS - the power of choice");
		logger.log(LogStatus.INFO, "Navigated to Log In Page and Title verified");
		sign.SignInAct(useremail, userpass);
		logger.log(LogStatus.INFO, "Loging In with email: "+useremail+" password: "+userpass);
		account = new AccountPage(driver);
		Assert.assertEquals(account.Accountttylverify(), "My account - eBFS - the power of choice");
		logger.log(LogStatus.INFO, "Navigated to My Account Page and Title verified");
		Assert.assertEquals(account.Accverify(), username);
		logger.log(LogStatus.INFO, "User "+username+" is navigated to "+account.Accverify()+" Account");
		account.LogOutClick();
		logger.log(LogStatus.INFO, username+" Logged Out");
		logger.log(LogStatus.PASS, "Passed");
		
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
 
	
	
	
	
  @Test(enabled=false)
  public void EndToEndPro() {
	  logger = report.startTest("End to End test begain");
	  home = new HomePage(driver);
	 logger.log(LogStatus.INFO, "Navigated to HomePage");
	  double oldprice = home.Feature();
	  logger.log(LogStatus.INFO, "Total cost of the product must be: "+oldprice+" including $2.00 Shipping cost");
	  logger.log(LogStatus.INFO, "Clicked Add to Cart");
	  home.Prossed();
		 logger.log(LogStatus.INFO, "Clicked Proceed To Checkout");
		 logger.log(LogStatus.INFO, "<font style=\"color:red;\">Navigated to next page forcefully using driver.navigate().to(URL)</font>");
	  summary = new SummaryPage(driver);
	  logger.log(LogStatus.INFO, "Navigated to Summary Page");
	  
	  /////////////Testing ScreenShot using Koly Script//////////////////////
	  String photo = logger.addScreenCapture(Utitty.captureScreenshot(driver,"TestingIT"));
	  logger.log(LogStatus.INFO,"Testing ScreenShot",photo);
	  /////////////////////////////////////////////////////////////////////
	  
	  Assert.assertEquals(summary.CheckOutTitle(), "Your shopping cart");
	  summary.CheckOut();
	  logger.log(LogStatus.INFO, "Clicked Check Out");
	  Assert.assertEquals(summary.SignInTtl(), "Authentication");
	  summary.SignIn("saju@gmail.com", "sajuuddin");
	  logger.log(LogStatus.INFO, "User is Signned In");
	  Assert.assertEquals(summary.AfterBill(), "YOUR BILLING ADDRESS");
	  summary.ClickCheckOut();
	  logger.log(LogStatus.INFO, "Selected Billing and Shipping Address");
	  Assert.assertEquals(summary.termAndConditionTtl(),"Shipping:");
	  summary.termAndCondition();
	  
	  /////////////Testing ScreenShot using Deepu Script//////////////////////
	  String pic = logger.addScreenCapture(Utility.captureScreenshot(driver,"TestingTerms"));
	  logger.log(LogStatus.INFO,"Testing ScreenShot",pic);
	  /////////////////////////////////////////////////////////////////////
	  
	  logger.log(LogStatus.INFO, "Accepted Terms and Conditions");
	  Assert.assertEquals(summary.paymentMethodTtl(), "Your payment method");
	  summary.paymentMethod();
	  logger.log(LogStatus.INFO, "Selected Payment By Check");
	  
	  Assert.assertEquals(summary.confirmationTtl(), "Order confirmation");
	  summary.confirmation();
	  logger.log(LogStatus.INFO, "Navigated to Order Confirmation Page");
	  
	  Assert.assertEquals(summary.confirmMassage(), "Your order on eBFS - the power of choice is complete.");
	  logger.log(LogStatus.INFO, "Confirmation Message Displayed: "+summary.confirmMassage());
	 Assert.assertEquals(oldprice, summary.Cost());
	 logger.log(LogStatus.INFO, "<font style=\"color:red;\">Old Price is: $"+oldprice+" AND New Price is: $"+summary.Cost()+"</font>");
	  logger.log(LogStatus.PASS, "This test script passed");
     
  
  }
  
  
  @BeforeMethod
  public void beforeMethod() {
	  driver.get("http://ebfs.bruteforcesolution.net/ebfs/index.php");
	 
	  driver.manage().window().maximize();
  }
 
  @AfterMethod
  public void tearDown(ITestResult result) throws Throwable{
	 if(result.getStatus()==ITestResult.FAILURE){
		 String image = logger.addScreenCapture(Utility.captureScreenshot(driver,result.getName()));
		 logger.log(LogStatus.FAIL,"Featured Product visibility",image);
	  }
	  report.endTest(logger);
	  report.flush();
	  
  }
  
  @BeforeTest
  public void beforeTest() {
	  driver = new FirefoxDriver();
	  report = new ExtentReports("C:\\Users\\Samima\\workspace\\EndToEnd\\reports\\testreport.html");
	  
	  report.addSystemInfo("Selenium Version", "2.53.0");
	  report.addSystemInfo("Environment", "QA");

	  
  }

  @AfterTest
  public void afterTest() {
	  driver.get("C:\\Users\\Samima\\workspace\\EndToEnd\\reports\\testreport.html");
  }

}
