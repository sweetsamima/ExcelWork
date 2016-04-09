package com.page;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utitty {
	
	public static String captureScreenshot(WebDriver driver, String screenshot){
		try {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File sorc=ts.getScreenshotAs(OutputType.FILE);
		
			FileUtils.copyFile(sorc,new File("./shot/"+screenshot+".png"));
			
			//FileUtils.copyFile((File)((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE),new File("./shot/"+screenshot+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "../shot/"+screenshot+".png";
		
		
	}

}
