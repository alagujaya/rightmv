package com.businessflow;

import jxl.Sheet;

//java imports
import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.engine.SeleniumSetup;

import com.pages.*;

public class SalesFlow extends SeleniumSetup {
	

	
	//initialise web driver to null
	public static WebDriver driver = null;
	public static int timeoutInSeconds = 30;
	public static Map<String, String> DATA_MAP;


	public void salesFlow(String Script_id, String ROW) throws Exception{
		

		homepage hp = new homepage(driver);
		hp.EnterSearchText("London");
		hp.ClickSalesButton();

		
	}		
	
}
