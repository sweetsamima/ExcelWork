package com.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SummaryPage {
	WebDriver driver;	
	public SummaryPage (WebDriver a){
	driver=a;	
}	
	By checkOut = By.xpath(".//*[@id='center_column']/p[2]/a[1]/span");
      By shoppingCart = By.xpath(".//*[@id='columns']/div[1]/span[2]");
    By email = By.xpath(".//*[@id='email']");
    By pass = By.id("passwd");
    By submit = By.xpath(".//*[@id='SubmitLogin']");
       By pagettl = By.xpath(".//*[@id='columns']/div[1]/span[2]");
       By billAddres = By.xpath(".//*[@id='address_invoice']/li[1]/h3");
    By afterBill =  By.xpath(".//*[@name='processAddress']");
       By shipping = By.xpath(".//*[@id='columns']/div[1]/span[2]");
    By termOfService = By.xpath(".//*[@id='cgv']");
    By checkoutProcess =By.xpath(".//*[@id='form']/p/button");
       By paymentMethod = By.xpath(".//*[@id='columns']/div[1]/span[2]");
       By payCheck = By.className("cheque");
       By checkPayment = By.xpath(".//*[@id='columns']/div[1]/span[2]");
    By orderConfirm = By.xpath(".//*[@id='cart_navigation']/button");
      By confirmation = By.xpath(".//*[@id='center_column']/h1");
      By finalCost = By.xpath(".//*[@class='price']");
      By confirmMass = By.xpath(".//*[@id='center_column']/p[1]");
      

    public String CheckOutTitle(){
    	String title= driver.findElement(shoppingCart).getText();
    	return title;
    }
    public void CheckOut(){
    	driver.findElement(checkOut).click();
    }
    public String SignInTtl(){
    	String Pagettl= driver.findElement(pagettl).getText();
    	return Pagettl;
    }
    
    public void SignIn(String emal,String passwd){
    	driver.findElement(email).sendKeys(emal);
    	driver.findElement(pass).sendKeys(passwd);
    	driver.findElement(submit).click();
    }
    
    public String AfterBill(){
    	String bill= driver.findElement(billAddres).getText();
    	return bill;
    }
    
    public void ClickCheckOut(){
    	
    	driver.findElement(afterBill).click();

    }
    
    public String termAndConditionTtl(){
    	String pttl = driver.findElement(shipping).getText();
    	return pttl;
    	  }
    public void termAndCondition(){
    	driver.findElement(termOfService).click();
    	driver.findElement(checkoutProcess).click();
    }
	
    public String paymentMethodTtl(){
    	String methodttl = driver.findElement(paymentMethod).getText();
	  return methodttl;
    }
    
    public void paymentMethod(){
    	driver.findElement(payCheck).click();
    	driver.findElement(orderConfirm).click();
    }
    
    public String confirmationTtl(){
    	String check = driver.findElement(checkPayment).getText();
    	return check;
    }
    
    
    public void confirmation(){
    	driver.findElement(confirmation).click();
    }
    
    public String confirmMassage(){
    	return driver.findElement(confirmMass).getText();
    }
    
    public double Cost(){
    	
        String finalc = driver.findElement(finalCost).getText(); // example String
        //Removes $ sign from String text
        String b = finalc.substring(1);
        //Converts value of String b into Double
        double v = Double.parseDouble(b);
        return v;
    }

}